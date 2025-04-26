package com.ecommerce.service;

import com.ecommerce.model.Transaction;
import com.ecommerce.model.TransactionStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;
import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionGenerator {

    private final KafkaTemplate<String, Transaction> kafkaTemplate;
    private final Random random = new Random();
    private static final String TOPIC = "transactions";

    private static final String[] PAYMENT_METHODS = {"CREDIT_CARD", "DEBIT_CARD", "UPI", "NET_BANKING"};
    private static final String[] CURRENCIES = {"USD", "EUR", "GBP", "INR"};

    @Scheduled(fixedRate = 5) // Generate 200 transactions per second
    public void generateTransaction() {
        Transaction transaction = Transaction.builder()
                .orderId(UUID.randomUUID().toString())
                .transactionId(UUID.randomUUID().toString())
                .customerId("CUST-" + random.nextInt(1000))
                .amount(random.nextDouble() * 1000)
                .currency(CURRENCIES[random.nextInt(CURRENCIES.length)])
                .timestamp(System.currentTimeMillis())
                .paymentMethod(PAYMENT_METHODS[random.nextInt(PAYMENT_METHODS.length)])
                .status(TransactionStatus.PENDING)
                .description("Test transaction")
                .build();

        try {
            kafkaTemplate.send(TOPIC, transaction.getOrderId(), transaction);
            log.info("Generated transaction: {}", transaction);
        } catch (Exception e) {
            log.error("Error sending transaction to Kafka: {}", e.getMessage());
        }
    }
} 