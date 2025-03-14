package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the RefundResponse class.
 */
public class RefundResponseTest {

    /**
     * Tests the RefundResponse constructor and setters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testRefundResponse() {
        RefundResponse response = new RefundResponse();
        response.setStatus("REFUNDED");

        assertEquals("REFUNDED", response.getStatus());
    }
}