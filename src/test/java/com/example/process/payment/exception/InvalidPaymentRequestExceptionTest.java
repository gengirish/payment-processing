package com.example.process.payment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the InvalidPaymentRequestException class.
 */
public class InvalidPaymentRequestExceptionTest {

    /**
     * Tests the InvalidPaymentRequestException constructor and message.
     */
    @Test
    public void testInvalidPaymentRequestException() {
        String errorMessage = "Invalid payment request";
        InvalidPaymentRequestException exception = assertThrows(InvalidPaymentRequestException.class, () -> {
            throw new InvalidPaymentRequestException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}