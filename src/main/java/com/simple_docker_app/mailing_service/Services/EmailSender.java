package com.simple_docker_app.mailing_service.Services;
import com.simple_docker_app.mailing_service.Domain.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import javax.json.Json;
import javax.json.JsonObject;
@Service
public class EmailSender {
    @Autowired
    EmailService emailService;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    private Environment env;

    @Value("${sendgrid.api.key}")
    String sendgridApiKey;
    @Value("${sendgrid.api.url}")
    String sendgridApiUrl;


    // this sends greeting email
    public void sendMail(Email email) {
        String message = "<p>hello!</p>, your account has been <b>registered</b>, this is mail was sent by gridmail";
        sendSendGridMail(email, message);
        email.setSent(true);
        emailService.saveEmail(email);
    }

    public void sendMail(Email email, String message) {
        sendSendGridMail(email, message);
        emailService.saveEmail(email);
    }

    //building platform specific messages
    public void sendSendGridMail(Email email, String message) {
        String SENDGRID_SEND_URL = sendgridApiUrl + "/v3/mail/send";
        String DEFAULT_WELCOME_MESSAGE_SUBJECT = "welcome (spring service registration)";

        JsonObject object = Json.createObjectBuilder()
                .add("personalizations", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                        .add("to", Json.createArrayBuilder()
                                .add(Json.createObjectBuilder()
                                        .add("email", email.getSentToUser().get(0).getEmail()) // this is only for 1 receiver
                                )
                        ))
                )
                .add("from", Json.createObjectBuilder()
                        .add("email", email.getSentFromUser().getEmail())
                        .add("name", email.getSentFromUser().getName()))
                .add("subject", DEFAULT_WELCOME_MESSAGE_SUBJECT)
                .add("content", Json.createArrayBuilder()
                        .add(Json.createObjectBuilder()
                                .add("type", "text/html")
                                .add("value", message)))
                .build();

        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(sendgridApiKey);
        headers.setContentType(MediaType.APPLICATION_JSON);

        HttpEntity<String> entity = new HttpEntity<>(object.toString(), headers);

        restTemplate.postForEntity(SENDGRID_SEND_URL, entity, String.class);
    }
}
