package com.simple_docker_app.mailing_service.Repos;

import com.simple_docker_app.mailing_service.Domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    Optional<User> findUserByEmail(String mail);
}
