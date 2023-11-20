package com.simple_docker_app.mailing_service.simple_tutorial;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {
}
