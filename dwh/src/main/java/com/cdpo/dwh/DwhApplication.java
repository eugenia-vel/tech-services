package com.cdpo.dwh;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
public class DwhApplication {

	public static void main(String[] args) {
		SpringApplication.run(DwhApplication.class, args);
	}

}
