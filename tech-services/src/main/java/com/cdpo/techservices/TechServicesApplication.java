package com.cdpo.techservices;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.jdbc.autoconfigure.DataSourceAutoConfiguration;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories("com...jpa")
@EnableFeignClients
@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
public class TechServicesApplication {

	public static void main(String[] args) {
		SpringApplication.run(TechServicesApplication.class, args);
	}

}