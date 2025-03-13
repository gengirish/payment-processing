package com.example.process.payment.exception;

public class InvalidPaymentRequestException extends RuntimeException {
    public InvalidPaymentRequestException(String message) {
        super(message);
    }
}