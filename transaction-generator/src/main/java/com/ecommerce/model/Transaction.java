package com.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Transaction {
    private String orderId;
    private String transactionId;
    private String customerId;
    private double amount;
    private String currency;
    private long timestamp;
    private String paymentMethod;
    private TransactionStatus status;
    private String description;
} 