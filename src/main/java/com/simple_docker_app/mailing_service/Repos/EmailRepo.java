package com.simple_docker_app.mailing_service.Repos;

import com.simple_docker_app.mailing_service.Domain.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmailRepo extends JpaRepository<Email, Long> {
}
