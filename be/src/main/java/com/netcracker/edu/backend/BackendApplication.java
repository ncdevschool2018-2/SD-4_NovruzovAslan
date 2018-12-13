package com.netcracker.edu.backend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
//@ComponentScan("com.netcracker.edu.backend.scheduler")
//@ComponentScan("com.netcracker.edu.backend.controller")
//@EntityScan("com.netcracker.edu.backend.entity")
//@EnableJpaRepositories("com.netcracker.edu.backend.repository")
@EnableScheduling
public class BackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(BackendApplication.class, args);
	}
}
