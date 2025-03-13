package com.example.process.payment.controller;

import com.example.process.payment.dto.*;
import com.example.process.payment.service.PaymentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    @Test
    public void testInitiatePayment() throws Exception {
        PaymentRequest request = new PaymentRequest();
        request.setAmount(100.0);
        request.setCurrency("USD");
        request.setCustomerEmail("test@example.com");

        PaymentResponse response = new PaymentResponse("12345", "http://payment.url");

        when(paymentService.initiatePayment(any(PaymentRequest.class))).thenReturn(response);

        mockMvc.perform(post("/api/payments/initiate")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"amount\":100.0,\"currency\":\"USD\",\"customerEmail\":\"test@example.com\"}"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("12345"))
                .andExpect(jsonPath("$.paymentUrl").value("http://payment.url"));
    }

    @Test
    public void testGetPaymentStatus() throws Exception {
        PaymentStatusResponse response = new PaymentStatusResponse("12345", "SUCCESS");

        when(paymentService.getPaymentStatus("12345")).thenReturn(response);

        mockMvc.perform(get("/api/payments/12345/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("12345"))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    @Test
    public void testProcessRefund() throws Exception {
        RefundResponse response = new RefundResponse();
        response.setStatus("REFUNDED");

        when(paymentService.processRefund("12345", 50.0)).thenReturn(response);

        mockMvc.perform(post("/api/payments/12345/refund")
                        .param("amount", "50.0"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.status").value("REFUNDED"));
    }

    @Test
    public void testHandleWebhook() throws Exception {
        mockMvc.perform(post("/api/payments/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"event\":\"payment.success\",\"transaction_id\":\"12345\"}"))
                .andExpect(status().isOk());
    }
}