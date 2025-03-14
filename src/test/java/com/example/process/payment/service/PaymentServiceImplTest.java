package com.example.process.payment.service;

import com.example.process.payment.config.PaymentGatewayClient;
import com.example.process.payment.dto.*;
import com.example.process.payment.exception.InvalidPaymentRequestException;
import com.example.process.payment.exception.PaymentNotFoundException;
import com.example.process.payment.model.Payment;
import com.example.process.payment.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Map;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

/**
 * Unit tests for the PaymentServiceImpl class.
 */
public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentGatewayClient paymentGatewayClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and injects them into the paymentService instance.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    /**
     * Tests the initiatePayment method.
     * Verifies that a payment is initiated and saved correctly.
     */
    @Test
    public void testInitiatePayment() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setCustomerEmail("test@example.com");

        PaymentGatewayResponse gatewayResponse = new PaymentGatewayResponse();
        gatewayResponse.setTransactionId("12345");
        gatewayResponse.setPaymentUrl("http://payment.url");

        when(paymentGatewayClient.createPayment(any(PaymentRequest.class))).thenReturn(gatewayResponse);

        PaymentResponse response = paymentService.initiatePayment(request);

        assertEquals("12345", response.getTransactionId());
        assertEquals("http://payment.url", response.getPaymentUrl());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    /**
     * Tests the getPaymentStatus method.
     * Verifies that the payment status is retrieved correctly.
     */
    @Test
    public void testGetPaymentStatus() {
        Payment payment = new Payment();
        payment.setStatus("SUCCESS");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        PaymentStatusResponse response = paymentService.getPaymentStatus("12345");

        assertEquals("12345", response.getTransactionId());
        assertEquals("SUCCESS", response.getStatus());
    }

    /**
     * Tests the getPaymentStatus method when the payment is not found.
     * Verifies that a PaymentNotFoundException is thrown.
     */
    @Test
    public void testGetPaymentStatus_NotFound() {
        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> paymentService.getPaymentStatus("12345"));
    }

    /**
     * Tests the processRefund method.
     * Verifies that a refund is processed and saved correctly.
     */
    @Test
    public void testProcessRefund() {
        Payment payment = new Payment();
        payment.setStatus("SUCCESS");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        RefundResponse refundResponse = new RefundResponse();
        refundResponse.setStatus("REFUNDED");

        when(paymentGatewayClient.processRefund("12345", 50.0)).thenReturn(refundResponse);

        RefundResponse response = paymentService.processRefund("12345", 50.0);

        assertEquals("REFUNDED", response.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
    }

    /**
     * Tests the processRefund method with an invalid request.
     * Verifies that an InvalidPaymentRequestException is thrown.
     */
    @Test
    public void testProcessRefund_InvalidRequest() {
        Payment payment = new Payment();
        payment.setStatus("PENDING");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        assertThrows(InvalidPaymentRequestException.class, () -> paymentService.processRefund("12345", 50.0));
    }

    /**
     * Tests the handleWebhookEvent method.
     * Verifies that the payment status is updated correctly based on the webhook event.
     */
    @Test
    public void testHandleWebhookEvent() {
        Payment payment = new Payment();
        payment.setStatus("PENDING");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        paymentService.handleWebhookEvent(Map.of("event", "payment.success", "transaction_id", "12345"));

        assertEquals("SUCCESS", payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }
}