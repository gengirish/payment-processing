package com.example.process.payment.config;

import com.example.process.payment.dto.PaymentGatewayResponse;
import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.RefundResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpEntity;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

public class PaymentGatewayClientTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private PaymentGatewayClient paymentGatewayClient;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreatePayment() {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setCustomerEmail("test@example.com");

        PaymentGatewayResponse expectedResponse = new PaymentGatewayResponse();
        expectedResponse.setTransactionId("12345");
        expectedResponse.setPaymentUrl("http://payment.url");

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(expectedResponse);

        PaymentGatewayResponse actualResponse = paymentGatewayClient.createPayment(request);

        assertEquals(expectedResponse.getTransactionId(), actualResponse.getTransactionId());
        assertEquals(expectedResponse.getPaymentUrl(), actualResponse.getPaymentUrl());
    }

    @Test
    public void testProcessRefund() {
        String transactionId = "12345";
        Double amount = 50.0;

        RefundResponse expectedResponse = new RefundResponse();
        expectedResponse.setStatus("REFUNDED");

        when(restTemplate.postForObject(any(String.class), any(HttpEntity.class), any(Class.class)))
                .thenReturn(expectedResponse);

        RefundResponse actualResponse = paymentGatewayClient.processRefund(transactionId, amount);

        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }
}