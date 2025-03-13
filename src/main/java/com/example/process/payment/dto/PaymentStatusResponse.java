package com.example.process.payment.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Represents the response for a refund request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PaymentStatusResponse {
    private String transactionId;
    private String status;
}
