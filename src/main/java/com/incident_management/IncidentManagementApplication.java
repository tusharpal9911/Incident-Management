package com.incident_management;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@ComponentScan("com.nervy.dialer.domain")
@EntityScan(basePackages = "com.incident_management.entity")
@EnableJpaRepositories ("domain.repositroy-package") 
//@EnableJpaRepositories(basePackages = "com.incident_management.repository")
public class IncidentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(IncidentManagementApplication.class, args);
    }

}
