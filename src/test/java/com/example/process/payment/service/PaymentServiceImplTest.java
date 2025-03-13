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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class PaymentServiceImplTest {

    @Mock
    private PaymentRepository paymentRepository;

    @Mock
    private PaymentGatewayClient paymentGatewayClient;

    @InjectMocks
    private PaymentServiceImpl paymentService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

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

    @Test
    public void testGetPaymentStatus() {
        Payment payment = new Payment();
        payment.setStatus("SUCCESS");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        PaymentStatusResponse response = paymentService.getPaymentStatus("12345");

        assertEquals("12345", response.getTransactionId());
        assertEquals("SUCCESS", response.getStatus());
    }

    @Test
    public void testGetPaymentStatus_NotFound() {
        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.empty());

        assertThrows(PaymentNotFoundException.class, () -> paymentService.getPaymentStatus("12345"));
    }

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

    @Test
    public void testProcessRefund_InvalidRequest() {
        Payment payment = new Payment();
        payment.setStatus("PENDING");

        when(paymentRepository.findByTransactionId("12345")).thenReturn(Optional.of(payment));

        assertThrows(InvalidPaymentRequestException.class, () -> paymentService.processRefund("12345", 50.0));
    }

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