package com.example.process.payment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Unit tests for the PaymentNotFoundException class.
 */
public class PaymentNotFoundExceptionTest {

    /**
     * Tests the PaymentNotFoundException constructor and message.
     */
    @Test
    public void testPaymentNotFoundException() {
        String errorMessage = "Payment not found";
        PaymentNotFoundException exception = assertThrows(PaymentNotFoundException.class, () -> {
            throw new PaymentNotFoundException(errorMessage);
        });

        assertEquals(errorMessage, exception.getMessage());
    }
}