package com.simple_docker_app.mailing_service.Services;

import com.simple_docker_app.mailing_service.Domain.Email;
import com.simple_docker_app.mailing_service.Domain.User;
import com.simple_docker_app.mailing_service.Repos.EmailRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class EmailService {
    @Autowired
    RestTemplate restTemplate;
    @Autowired
    EmailRepo emailRepo;
    @Autowired
    UserSerivice userSerivice;

    public Email createAndSaveMail(User sendingUser, User receivingUser, String message) {
        Email email = new Email();
        return emailRepo.save(email);
    }

    public Email welcomeEmail(User createdUser) {
        Email email = new Email();
        User root = userSerivice.getRootUser();
        email.setSentFromUser(root);
        List<User> modifiableReceiverUserList = new ArrayList<>();
        modifiableReceiverUserList.add(createdUser);
        email.setSentToUser(modifiableReceiverUserList);
        email.setTimestamp(LocalDateTime.now());
        email.setSent(false);
        return emailRepo.save(email);
    }

    public void saveEmail(Email email) {
        emailRepo.save(email);
    }

    public List<Email> getAllMail() {
        return emailRepo.findAll();
    }
}
