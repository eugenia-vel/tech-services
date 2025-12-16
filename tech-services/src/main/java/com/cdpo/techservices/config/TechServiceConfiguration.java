package com.cdpo.techservices.config;

import com.cdpo.techservices.exceptions.DwhException;
import feign.Response;
import feign.Retryer;
import feign.codec.ErrorDecoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.RestClient;

@Configuration
public class TechServiceConfiguration {

//    @Bean
//    public RestClient notifierClient() {
//        return RestClient.builder()
//                .baseUrl("http://localhost:8083/api/v1/notifier")
//                .build();
//    }

    @Bean
    public Retryer retryer() {
        return new Retryer.Default(1000, 3000, 3);
    }

    @Bean
    public ErrorDecoder customErrorDecoder() {
        return new ErrorDecoder() {
            @Override
            public Exception decode(String methodKey, Response response) {
                if (response.status() == 404) {
                    return new DwhException(HttpStatus.NOT_FOUND, "Ошибка при отправке данных в сервис сбора статистики");
                }
                return null;
            }
        };
    }
}
