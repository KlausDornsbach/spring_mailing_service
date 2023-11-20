package com.simple_docker_app.mailing_service.Configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.web.client.RestTemplate;

@Configuration
public class Configurations {
    @Bean
    public RestTemplate handleRestTemplateInstantiation() {
        return new RestTemplate();
    }
}
