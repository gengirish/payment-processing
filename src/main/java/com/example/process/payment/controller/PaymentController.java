package com.example.process.payment.controller;

import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.PaymentResponse;
import com.example.process.payment.dto.PaymentStatusResponse;
import com.example.process.payment.dto.RefundResponse;
import com.example.process.payment.service.PaymentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * REST controller for handling payment-related requests.
 */
@RestController
@RequestMapping("/api/payments")
public class PaymentController {

    @Autowired
    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    /**
     * Initiates a payment.
     *
     * @param request the payment request
     * @return the response entity with payment response
     */
    @PostMapping("/initiate")
    public ResponseEntity<PaymentResponse> initiatePayment(@Valid @RequestBody PaymentRequest request) {
        return ResponseEntity.ok(paymentService.initiatePayment(request));
    }

    /**
     * Gets the status of a payment.
     *
     * @param transactionId the transaction ID
     * @return the response entity with payment status response
     */
    @GetMapping("/{transactionId}/status")
    public ResponseEntity<PaymentStatusResponse> getPaymentStatus(@PathVariable String transactionId) {
        return ResponseEntity.ok(paymentService.getPaymentStatus(transactionId));
    }

    /**
     * Processes a refund.
     *
     * @param transactionId the transaction ID
     * @param amount        the refund amount
     * @return the response entity with refund response
     */
    @PostMapping("/{transactionId}/refund")
    public ResponseEntity<RefundResponse> processRefund(@PathVariable String transactionId, @RequestParam Double amount) {
        return ResponseEntity.ok(paymentService.processRefund(transactionId, amount));
    }

    /**
     * Handles webhook events.
     *
     * @param payload the webhook payload
     * @return the response entity
     */
    @PostMapping("/webhook")
    public ResponseEntity<Void> handleWebhook(@RequestBody Map<String, Object> payload) {
        paymentService.handleWebhookEvent(payload);
        return ResponseEntity.ok().build();
    }
}