package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TransactionGeneratorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionGeneratorApplication.class, args);
    }
} 