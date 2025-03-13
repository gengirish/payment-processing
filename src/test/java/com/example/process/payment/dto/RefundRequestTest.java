package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefundRequestTest {

    @Test
    public void testRefundRequest() {
        RefundRequest request = new RefundRequest("12345", 50.0);

        assertEquals("12345", request.getTransactionId());
        assertEquals(50.0, request.getAmount());
    }
}