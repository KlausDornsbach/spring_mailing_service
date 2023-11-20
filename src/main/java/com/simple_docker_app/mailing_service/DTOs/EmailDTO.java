package com.simple_docker_app.mailing_service.DTOs;

import java.util.List;

public record EmailDTO(String sendingUserEmail, List<String> receivingUserEmail) {
}
