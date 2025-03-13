package com.example.process.payment.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PaymentGatewayResponseTest {

    @Test
    public void testPaymentGatewayResponse() {
        PaymentGatewayResponse response = new PaymentGatewayResponse("12345", "http://payment.url", "SUCCESS");

        assertEquals("12345", response.getTransactionId());
        assertEquals("http://payment.url", response.getPaymentUrl());
        assertEquals("SUCCESS", response.getStatus());
    }
}