package com.example.process.payment.dto;

/**
 * Represents the response for a refund request.
 */
public class PaymentStatusResponse {
    private String transactionId;
    private String status;

    public PaymentStatusResponse() {
    }

    public PaymentStatusResponse(String transactionId, String status) {
        this.transactionId = transactionId;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
