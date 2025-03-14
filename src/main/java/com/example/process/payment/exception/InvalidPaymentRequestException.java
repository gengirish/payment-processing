package com.example.process.payment.exception;

/**
 * Exception thrown when a payment request is invalid.
 */
public class InvalidPaymentRequestException extends RuntimeException {

    /**
     * Constructs a new InvalidPaymentRequestException with the specified detail message.
     *
     * @param message the detail message
     */
    public InvalidPaymentRequestException(String message) {
        super(message);
    }
}