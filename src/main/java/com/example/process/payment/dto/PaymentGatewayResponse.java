package com.example.process.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response from the payment gateway.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentGatewayResponse {
    private String transactionId;
    private String paymentUrl;
    private String status;
}