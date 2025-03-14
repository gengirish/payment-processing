package com.example.process.payment.model;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the Payment class.
 */
public class PaymentTest {

    /**
     * Tests the Payment constructor and getters/setters.
     * Verifies that the fields are set correctly.
     */
    @Test
    public void testPayment() {
        Payment payment = new Payment();
        payment.setId(1L);
        payment.setTransactionId("txn123");
        payment.setStatus("COMPLETED");
        payment.setAmount(150.0);
        payment.setCurrency("USD");
        payment.setCustomerEmail("customer@example.com");
        payment.setProvider("ProviderX");
        payment.setPaymentUrl("http://payment.url");
        payment.setExternalPaymentId("ext123");
        LocalDateTime now = LocalDateTime.now();
        payment.setCreatedAt(now);
        payment.setUpdatedAt(now);

        assertEquals(1L, payment.getId());
        assertEquals("txn123", payment.getTransactionId());
        assertEquals("COMPLETED", payment.getStatus());
        assertEquals(150.0, payment.getAmount());
        assertEquals("USD", payment.getCurrency());
        assertEquals("customer@example.com", payment.getCustomerEmail());
        assertEquals("ProviderX", payment.getProvider());
        assertEquals("http://payment.url", payment.getPaymentUrl());
        assertEquals("ext123", payment.getExternalPaymentId());
        assertEquals(now, payment.getCreatedAt());
        assertEquals(now, payment.getUpdatedAt());
    }
}