package com.archivage.demo.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDateTime;

@Document(collection = "paymentIntent")
public class PaymentIntent {

    @Id
    private String id;
    private String userId;
    private String provider; // "stripe" ou "paypal"
    private String status;   // "succeeded", "failed", "pending"
    private Long amount;     // en cents
    private String currency; // "usd", "eur", etc.
    private String transactionId; // ID retourné par Stripe/PayPal
    private LocalDateTime createdAt;

    public PaymentIntent() {
    }


    public PaymentIntent(String userId, String provider, String status, Long amount, String currency, String transactionId, LocalDateTime createdAt) {
        this.userId = userId;
        this.provider = provider;
        this.status = status;
        this.amount = amount;
        this.currency = currency;
        this.transactionId = transactionId;
        this.createdAt = createdAt;
    }

    public PaymentIntent(String userId, String provider, String status, Long amount, String currency, String transactionId) {
        this.userId = userId;
        this.provider = provider;
        this.status = status;
        this.amount = amount;
        this.currency = currency;
        this.transactionId = transactionId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Long getAmount() {
        return amount;
    }

    public void setAmount(Long amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
