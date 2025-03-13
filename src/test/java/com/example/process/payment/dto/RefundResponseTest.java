package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RefundResponseTest {

    @Test
    public void testRefundResponse() {
        RefundResponse response = new RefundResponse();
        response.setStatus("REFUNDED");

        assertEquals("REFUNDED", response.getStatus());
    }
}