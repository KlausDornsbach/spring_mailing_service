package com.simple_docker_app.mailing_service.Services;

import com.simple_docker_app.mailing_service.DTOs.UserDTO;
import com.simple_docker_app.mailing_service.Domain.User;
import com.simple_docker_app.mailing_service.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserSerivice {
    @Autowired
    UserRepo userRepo;
    @Value("${root.user.email}")
    String rootUserMail;
    public User createAndSaveUser(UserDTO userDTO) {
        User user = new User(userDTO);
        user = userRepo.save(user);
        return user;
    }

    public User getRootUser() {
        Optional<User> rootUser = userRepo.findUserByEmail(rootUserMail);
        if (rootUser.isEmpty()) {
            User newRoot = new User("root", rootUserMail);
            userRepo.save(newRoot);
            return newRoot;
        }
        return rootUser.get();
    }

    public User getUserByDTO(UserDTO userDTO) throws Exception {
        if (userRepo.findUserByEmail(userDTO.email()).isPresent()) {
            return userRepo.findUserByEmail(userDTO.email()).get();
        }
        throw new Exception("User mail not found on DB");
    }

    public List<User> getAllUsers() {
        return userRepo.findAll();
    }

    public UserDTO getUserDtoFromUser(User user) {
        return new UserDTO(user.getEmail(), user.getName(), "*******");
    }
}
