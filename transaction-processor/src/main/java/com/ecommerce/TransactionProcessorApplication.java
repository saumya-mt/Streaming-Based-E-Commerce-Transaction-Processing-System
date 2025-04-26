package com.ecommerce;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.kafka.annotation.EnableKafka;

@SpringBootApplication
@EnableKafka
@EnableCaching
public class TransactionProcessorApplication {
    public static void main(String[] args) {
        SpringApplication.run(TransactionProcessorApplication.class, args);
    }
} 