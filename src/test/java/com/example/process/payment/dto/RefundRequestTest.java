package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the RefundRequest class.
 */
public class RefundRequestTest {

    /**
     * Tests the RefundRequest constructor and getters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testRefundRequest() {
        RefundRequest request = new RefundRequest("12345", 50.0);

        assertEquals("12345", request.getTransactionId());
        assertEquals(50.0, request.getAmount());
    }
}