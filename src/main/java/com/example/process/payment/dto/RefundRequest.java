package com.example.process.payment.dto;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.NotBlank;

/**
 * Represents a refund request.
 */
public class RefundRequest {
    @NotBlank(message = "Transaction ID is required")
    private String transactionId;

    @NotNull(message = "Refund amount is required")
    @Positive(message = "Refund amount must be greater than zero")
    private Double amount;

    public RefundRequest(String transactionId, Double amount) {
        this.transactionId = transactionId;
        this.amount = amount;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }
}
