package com.vrn;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class DateApplication {
    public static void main(String[] args) {
        SpringApplication.run(DateApplication.class, args);
    }
}