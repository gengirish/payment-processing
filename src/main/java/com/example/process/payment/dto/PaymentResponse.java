package com.example.process.payment.dto;

/**
 * Represents the response for a refund request.
 */
public class PaymentResponse {
    private String transactionId;
    private String paymentUrl;

    public PaymentResponse(String transactionId, String paymentUrl) {
        this.transactionId = transactionId;
        this.paymentUrl = paymentUrl;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getTransactionId() {
        return transactionId;
    }
}
