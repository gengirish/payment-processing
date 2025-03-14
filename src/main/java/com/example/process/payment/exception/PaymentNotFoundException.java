package com.example.process.payment.exception;

/**
 * Exception thrown when a payment is not found.
 */
public class PaymentNotFoundException extends RuntimeException {

    /**
     * Constructs a new PaymentNotFoundException with the specified detail message.
     *
     * @param message the detail message
     */
    public PaymentNotFoundException(String message) {
        super(message);
    }
}