package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the PaymentRequest class.
 */
public class PaymentRequestTest {

    /**
     * Tests the PaymentRequest constructor and getters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testPaymentRequest() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setCustomerEmail("test@example.com");

        assertEquals(100.0, request.getAmount());
        assertEquals("USD", request.getCurrency());
        assertEquals("test@example.com", request.getCustomerEmail());
    }
}