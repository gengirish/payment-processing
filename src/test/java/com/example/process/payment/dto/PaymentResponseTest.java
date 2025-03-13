package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentResponseTest {

    @Test
    public void testPaymentResponse() {
        PaymentResponse response = new PaymentResponse("12345", "http://payment.url");

        assertEquals("12345", response.getTransactionId());
        assertEquals("http://payment.url", response.getPaymentUrl());
    }
}