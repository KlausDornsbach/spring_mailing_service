package com.simple_docker_app.mailing_service;

import com.simple_docker_app.mailing_service.Domain.User;
import com.simple_docker_app.mailing_service.Repos.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.event.EventListener;

import java.util.List;

@SpringBootApplication
@PropertySource("classpath:application.properties")
public class MailingServiceApplication {
	@Autowired
	private UserRepo repository;

//	@EventListener(ApplicationReadyEvent.class)
//	public void runAfterStartup() {
//		List allUsers = this.repository.findAll();
//		System.out.println("Number of users: " + allUsers.size());
//
//		User newUser = new User("asdf", "asdf");
//		System.out.println("Saving new user...");
//		this.repository.save(newUser);
//
//		allUsers = this.repository.findAll();
//		System.out.println("Number of users: " + allUsers.size());
//	}


	public static void main(String[] args) {
		SpringApplication.run(MailingServiceApplication.class, args);
	}

}
