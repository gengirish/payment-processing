package com.example.process.payment.dto;

/**
 * Represents the response from the payment gateway.
 */
public class PaymentGatewayResponse {
    private String transactionId;
    private String paymentUrl;
    private String status;

    public PaymentGatewayResponse() {

    }

    public PaymentGatewayResponse(String transactionId, String paymentUrl, String status) {
        this.transactionId = transactionId;
        this.paymentUrl = paymentUrl;
        this.status = status;
    }

    public String getTransactionId() {
        return transactionId;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "PaymentGatewayResponse{" +
                "transactionId='" + transactionId + '\'' +
                ", status='" + status + '\'' +
                '}';
    }
}