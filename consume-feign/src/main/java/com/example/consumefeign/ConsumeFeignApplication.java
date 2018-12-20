package com.example.consumefeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;


@SpringBootApplication
@EnableEurekaClient
@EnableFeignClients(basePackages = "com.example.consumefeign.service")
public class ConsumeFeignApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsumeFeignApplication.class, args);
    }

}

