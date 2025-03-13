package com.example.process.payment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents a refund request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RefundRequest {
    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Refund amount is required")
    @Positive(message = "Refund amount must be greater than zero")
    private Double amount;
}
