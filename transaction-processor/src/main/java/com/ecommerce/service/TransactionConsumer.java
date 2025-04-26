package com.ecommerce.service;

import com.ecommerce.model.Transaction;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.persistence.EntityManager;

@Slf4j
@Service
@RequiredArgsConstructor
public class TransactionConsumer {

    private final EntityManager entityManager;

    @KafkaListener(topics = "transactions", groupId = "${spring.kafka.consumer.group-id}")
    @Transactional
    public void consume(Transaction incomingTransaction) {
        log.info("Received transaction: {}", incomingTransaction);
        entityManager.persist(incomingTransaction);
        log.info("Saved transaction to database: {}", incomingTransaction);
    }
} 