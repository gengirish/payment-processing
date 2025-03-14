package com.example.process.payment.dto;

/**
 * Represents the response for a refund request.
 */

public class RefundResponse {
    private String transactionId;
    private Double refundedAmount;
    private String status;

    public RefundResponse() {
    }

    public RefundResponse(String transactionId, Double refundedAmount, String status) {
        this.transactionId = transactionId;
        this.refundedAmount = refundedAmount;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getRefundedAmount() {
        return refundedAmount;
    }

    public void setRefundedAmount(Double refundedAmount) {
        this.refundedAmount = refundedAmount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
