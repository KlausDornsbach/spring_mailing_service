package com.simple_docker_app.mailing_service.Controllers;

import com.simple_docker_app.mailing_service.DTOs.UserDTO;
import com.simple_docker_app.mailing_service.Domain.Email;
import com.simple_docker_app.mailing_service.Domain.User;
import com.simple_docker_app.mailing_service.Services.EmailSender;
import com.simple_docker_app.mailing_service.Services.EmailService;
import com.simple_docker_app.mailing_service.Services.UserSerivice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/mail")
public class AppController {

    @Autowired
    UserSerivice userSerivice;

    @Autowired
    EmailService emailService;

    @Autowired
    EmailSender emailSender;

    private static final String createdUserMessage = "Hello! This is a spring service and you have been registered";

    @PostMapping("/createUser")
    public UserDTO handleCreateUser(@RequestBody UserDTO user) {
        User newUser = userSerivice.createAndSaveUser(user);
        Email newEmail = emailService.welcomeEmail(newUser);
        emailSender.sendMail(newEmail);
        return user;
    }

    @GetMapping()
    public String hello() {
        return "hello!";
    }

    @PostMapping("/sendEmailToAllUsers")
    public List<UserDTO> handleSendMailToAllOthers(@RequestBody String message,
                                                   @RequestBody UserDTO sendingUserDTO) throws Exception{
//        User sendingUser = userSerivice.getUserByDTO(sendingUserDTO);
//        List<User> allUsers = userService.getAllUsers();
//        allUsers.stream().filter(user -> user.getEmail());
        return new ArrayList<>();
    }

//    @PostMapping("/sendEmailToUser")
//    public EmailDTO handleSendEmailToUser(@RequestBody String message,
//                                          @RequestBody UserDTO sendingUserDTO,
//                                          @RequestBody String receivingUserEmail) {
//        User receivingUser = userSerivice
//    }

    @GetMapping("/getAllSentMail")
    public List<Email> handleGetAllMail() {
        return emailService.getAllMail();
    }

    @GetMapping("/getAllUsers")
    public List<UserDTO> handleGetAllUsers() {
        return userSerivice.getAllUsers().stream().map(user -> userSerivice.getUserDtoFromUser(user)).collect(Collectors.toList());
    }
}