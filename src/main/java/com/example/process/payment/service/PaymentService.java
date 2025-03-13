package com.example.process.payment.service;

import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.PaymentResponse;
import com.example.process.payment.dto.PaymentStatusResponse;
import com.example.process.payment.dto.RefundResponse;

import java.util.Map;

/**
 * Service interface for payment operations.
 */
public interface PaymentService {
    /**
     * Initiates a payment.
     *
     * @param request the payment request
     * @return the payment response
     */
    PaymentResponse initiatePayment(PaymentRequest request);

    /**
     * Gets the status of a payment.
     *
     * @param transactionId the transaction ID
     * @return the payment status response
     */
    PaymentStatusResponse getPaymentStatus(String transactionId);

    /**
     * Processes a refund.
     *
     * @param transactionId the transaction ID
     * @param amount        the refund amount
     * @return the refund response
     */
    RefundResponse processRefund(String transactionId, Double amount);

    /**
     * Handles webhook events.
     *
     * @param payload the webhook payload
     */
    void handleWebhookEvent(Map<String, Object> payload);
}