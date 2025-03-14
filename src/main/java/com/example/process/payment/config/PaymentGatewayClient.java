package com.example.process.payment.config;

import com.example.process.payment.dto.PaymentGatewayResponse;
import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.RefundRequest;
import com.example.process.payment.dto.RefundResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

/**
 * Client for interacting with the payment gateway API.
 */
@Component
public class PaymentGatewayClient {

    private RestTemplate restTemplate = new RestTemplate();

    @Autowired
    public PaymentGatewayClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    /**
     * Creates a payment using the provided payment request.
     *
     * @param request the payment request containing payment details
     * @return the response from the payment gateway
     */
    public PaymentGatewayResponse createPayment(PaymentRequest request) {
        HttpEntity<PaymentRequest> entity = new HttpEntity<>(request);
        return restTemplate.postForObject("https://api.paymentgateway.com/payments", entity, PaymentGatewayResponse.class);
    }

    /**
     * Processes a refund for the specified transaction ID and amount.
     *
     * @param transactionId the ID of the transaction to refund
     * @param amount        the amount to refund
     * @return the response from the payment gateway
     */
    public RefundResponse processRefund(String transactionId, Double amount) {
        HttpEntity<RefundRequest> entity = new HttpEntity<>(new RefundRequest(transactionId, amount));
        return restTemplate.postForObject("https://api.paymentgateway.com/refunds", entity, RefundResponse.class);
    }
}