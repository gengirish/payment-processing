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

/**
 * Unit tests for the PaymentController class.
 */
public class PaymentControllerTest {

    @Mock
    private PaymentService paymentService;

    @InjectMocks
    private PaymentController paymentController;

    private MockMvc mockMvc;

    /**
     * Sets up the test environment before each test.
     * Initializes mocks and builds the MockMvc instance.
     */
    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(paymentController).build();
    }

    /**
     * Tests the initiatePayment method.
     * Verifies that a payment is initiated and the correct response is returned.
     */
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

    /**
     * Tests the getPaymentStatus method.
     * Verifies that the payment status is retrieved and the correct response is returned.
     */
    @Test
    public void testGetPaymentStatus() throws Exception {
        PaymentStatusResponse response = new PaymentStatusResponse("12345", "SUCCESS");

        when(paymentService.getPaymentStatus("12345")).thenReturn(response);

        mockMvc.perform(get("/api/payments/12345/status"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.transactionId").value("12345"))
                .andExpect(jsonPath("$.status").value("SUCCESS"));
    }

    /**
     * Tests the processRefund method.
     * Verifies that a refund is processed and the correct response is returned.
     */
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

    /**
     * Tests the handleWebhook method.
     * Verifies that the webhook event is handled correctly.
     */
    @Test
    public void testHandleWebhook() throws Exception {
        mockMvc.perform(post("/api/payments/webhook")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"event\":\"payment.success\",\"transaction_id\":\"12345\"}"))
                .andExpect(status().isOk());
    }
}