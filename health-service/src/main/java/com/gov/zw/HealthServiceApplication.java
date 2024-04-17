package com.gov.zw;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.web.reactive.config.EnableWebFlux;

@EnableWebFlux
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication
public class HealthServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(HealthServiceApplication.class, args);
	}
}
