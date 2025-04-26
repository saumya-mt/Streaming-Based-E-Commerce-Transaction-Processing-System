package com.ecommerce.model;

import lombok.Data;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TransactionDTO {
    private String orderId;
    private String transactionId;
    private String customerId;
    private double amount;
    private String currency;
    private long timestamp;
    private String paymentMethod;
    private TransactionStatus status;
    private String description;

    public enum TransactionStatus {
        PENDING,
        COMPLETED,
        FAILED,
        REFUNDED
    }
} 