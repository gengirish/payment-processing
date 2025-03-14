package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the PaymentStatusResponse class.
 */
public class PaymentStatusResponseTest {

    /**
     * Tests the PaymentStatusResponse constructor and setters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testPaymentStatusResponse() {
        PaymentStatusResponse response = new PaymentStatusResponse();
        response.setTransactionId("12345");
        response.setStatus("SUCCESS");

        assertEquals("12345", response.getTransactionId());
        assertEquals("SUCCESS", response.getStatus());
    }
}