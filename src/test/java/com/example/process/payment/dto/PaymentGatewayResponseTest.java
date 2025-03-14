package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the PaymentGatewayResponse class.
 */
public class PaymentGatewayResponseTest {

    /**
     * Tests the PaymentGatewayResponse constructor and getters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testPaymentGatewayResponse() {
        PaymentGatewayResponse response = new PaymentGatewayResponse("12345", "http://payment.url", "SUCCESS");

        assertEquals("12345", response.getTransactionId());
        assertEquals("http://payment.url", response.getPaymentUrl());
        assertEquals("SUCCESS", response.getStatus());
    }
}