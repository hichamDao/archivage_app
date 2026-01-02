package com.archivage.demo.model;

public class PaymentRequest {

    private Long amount;
    private String currency;
    private String userId;
    private String successUrl;
    private String cancelUrl;

    public PaymentRequest() {
    }

    public PaymentRequest(Long amount, String currency, String userId, String successUrl, String cancelUrl) {
        this.amount = amount;
        this.currency = currency;
        this.userId = userId;
        this.successUrl = successUrl;
        this.cancelUrl = cancelUrl;
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

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getSuccessUrl() {
        return successUrl;
    }

    public void setSuccessUrl(String successUrl) {
        this.successUrl = successUrl;
    }

    public String getCancelUrl() {
        return cancelUrl;
    }

    public void setCancelUrl(String cancelUrl) {
        this.cancelUrl = cancelUrl;
    }
}



