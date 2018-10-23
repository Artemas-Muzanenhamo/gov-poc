package com.gov.zw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.web.reactive.config.EnableWebFlux;

@SpringBootApplication
@EnableMongoRepositories
@EnableWebFlux
public class HealthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthServiceApplication.class, args);
	}
}
