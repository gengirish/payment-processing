package com.example.process.payment.config;

import com.example.process.payment.dto.PaymentGatewayResponse;
import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.RefundRequest;
import com.example.process.payment.dto.RefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client for interacting with the payment gateway API.
 * This class provides methods to create payments and process refunds.
 */
@Component
public class PaymentGatewayClient {

    private final RestTemplate restTemplate;

    /**
     * Constructs a new PaymentGatewayClient with the specified RestTemplate.
     *
     * @param restTemplate the RestTemplate to use for HTTP requests
     */
    @Autowired
    public PaymentGatewayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Creates a payment using the specified payment request.
     * Validates the request and sends it to the payment gateway API.
     *
     * @param request the payment request containing the amount, currency, and customer email
     * @return the response from the payment gateway API
     * @throws IllegalArgumentException if the amount is not positive
     */
    public PaymentGatewayResponse createPayment(PaymentRequest request) {
        if (request.getAmount() <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }

        return restTemplate.postForObject("https://api.paymentgateway.com/payments", request, PaymentGatewayResponse.class);
    }

    /**
     * Processes a refund for the specified transaction ID and amount.
     * Validates the inputs and sends the refund request to the payment gateway API.
     *
     * @param transactionId the ID of the transaction to refund
     * @param amount        the amount to refund
     * @return the response from the payment gateway API
     * @throws IllegalArgumentException if the amount is not positive or the transaction ID is empty
     */
    public RefundResponse processRefund(String transactionId, Double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Amount must be positive");
        }
        if (transactionId == null || transactionId.isEmpty()) {
            throw new IllegalArgumentException("Transaction ID must not be empty");
        }
        return restTemplate.postForObject("https://api.paymentgateway.com/refunds", new RefundRequest(transactionId, amount), RefundResponse.class);
    }
}