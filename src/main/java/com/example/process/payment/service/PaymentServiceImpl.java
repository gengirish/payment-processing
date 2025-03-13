package com.example.process.payment.service;

import com.example.process.payment.config.PaymentGatewayClient;
import com.example.process.payment.dto.*;
import com.example.process.payment.exception.InvalidPaymentRequestException;
import com.example.process.payment.exception.PaymentNotFoundException;
import com.example.process.payment.model.Payment;
import com.example.process.payment.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Implementation of the PaymentService interface.
 */
@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentGatewayClient paymentGatewayClient;

    /**
     * Initiates a payment.
     *
     * @param request the payment request
     * @return the payment response
     */
    @Override
    public PaymentResponse initiatePayment(PaymentRequest request) {
        Payment payment = new Payment();
        payment.setAmount(request.getAmount());
        payment.setCurrency(request.getCurrency());
        payment.setCustomerEmail(request.getCustomerEmail());
        payment.setStatus("PENDING");

        PaymentGatewayResponse gatewayResponse = paymentGatewayClient.createPayment(request);
        payment.setPaymentUrl(gatewayResponse.getPaymentUrl());
        payment.setExternalPaymentId(gatewayResponse.getTransactionId());

        paymentRepository.save(payment);

        return new PaymentResponse(gatewayResponse.getTransactionId(), gatewayResponse.getPaymentUrl());
    }

    /**
     * Gets the status of a payment.
     *
     * @param transactionId the transaction ID
     * @return the payment status response
     */
    @Override
    public PaymentStatusResponse getPaymentStatus(String transactionId) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Transaction ID not found"));

        return new PaymentStatusResponse(transactionId, payment.getStatus());
    }

    /**
     * Processes a refund.
     *
     * @param transactionId the transaction ID
     * @param amount        the refund amount
     * @return the refund response
     */
    @Override
    public RefundResponse processRefund(String transactionId, Double amount) {
        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Transaction ID not found"));

        if (!"SUCCESS".equals(payment.getStatus())) {
            throw new InvalidPaymentRequestException("Only successful payments can be refunded");
        }

        RefundResponse refundResponse = paymentGatewayClient.processRefund(transactionId, amount);
        payment.setStatus("REFUNDED");
        paymentRepository.save(payment);

        return refundResponse;
    }

    /**
     * Handles webhook events.
     *
     * @param payload the webhook payload
     */
    @Override
    public void handleWebhookEvent(Map<String, Object> payload) {
        String eventType = (String) payload.get("event");
        String transactionId = (String) payload.get("transaction_id");

        Payment payment = paymentRepository.findByTransactionId(transactionId)
                .orElseThrow(() -> new PaymentNotFoundException("Transaction ID not found"));

        if ("payment.success".equals(eventType)) {
            payment.setStatus("SUCCESS");
        } else if ("payment.failed".equals(eventType)) {
            payment.setStatus("FAILED");
        } else if ("payment.refunded".equals(eventType)) {
            payment.setStatus("REFUNDED");
        }

        paymentRepository.save(payment);
    }
}