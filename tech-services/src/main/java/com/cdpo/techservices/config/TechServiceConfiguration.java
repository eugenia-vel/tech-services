package com.cdpo.techservices.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestClient;

@Configuration
public class TechServiceConfiguration {

    @Bean
    public RestClient notifierClient() {
        return RestClient.builder()
                .baseUrl("http://localhost:8083/api/v1/notifier")
                .build();
    }
}
