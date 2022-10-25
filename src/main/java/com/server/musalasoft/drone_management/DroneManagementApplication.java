package com.server.musalasoft.drone_management;

import com.server.musalasoft.drone_management.audit.AuditorAwareImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing(auditorAwareRef = "auditorProvider")
@SpringBootApplication
public class DroneManagementApplication {

	public static void main(String[] args) {
		SpringApplication.run(DroneManagementApplication.class, args);
	}

	@Bean
	AuditorAware<String> auditorProvider() {
		return new AuditorAwareImpl();
	}

}
