package com.example.process.payment.config;

import com.example.process.payment.dto.PaymentGatewayResponse;
import com.example.process.payment.dto.PaymentRequest;
import com.example.process.payment.dto.RefundResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

/**
 * Test class for {@link PaymentGatewayClient}.
 * This class contains unit tests for the {@link PaymentGatewayClient} class, focusing on its interaction with the payment gateway API.
 */
class PaymentGatewayClientTest {

    private MockRestServiceServer mockServer;

    @InjectMocks
    private PaymentGatewayClient paymentGatewayClient;

    /**
     * Sets up the test environment before each test.
     * Initializes the mock server and the payment gateway client.
     */
    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        RestTemplate restTemplate = new RestTemplate();
        mockServer = MockRestServiceServer.createServer(restTemplate);
        paymentGatewayClient = new PaymentGatewayClient(restTemplate);
    }

    /**
     * Tests the successful creation of a payment.
     * Mocks the payment gateway response and verifies that the response matches the expected result.
     */
    @Test
    void testCreatePayment_Success() {
        // Arrange
        PaymentRequest request = new PaymentRequest(100.0, "order123", "USD");
        PaymentGatewayResponse expectedResponse = new PaymentGatewayResponse("txn123", "txn123", "SUCCESS");

        mockServer.expect(MockRestRequestMatchers.requestTo("https://api.paymentgateway.com/payments"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess("{\"url\":\"url\",\"transactionId\":\"txn123\",\"status\":\"SUCCESS\"}", MediaType.APPLICATION_JSON));

        // Act
        PaymentGatewayResponse actualResponse = paymentGatewayClient.createPayment(request);

        // Assert
        assertEquals(expectedResponse.getTransactionId(), actualResponse.getTransactionId());
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    /**
     * Tests the successful processing of a refund.
     * Mocks the payment gateway response and verifies that the response matches the expected result.
     */
    @Test
    void testProcessRefund_Success() {
        // Arrange
        String transactionId = "txn123";
        Double amount = 50.0;
        RefundResponse expectedResponse = new RefundResponse("refund123", amount, "SUCCESS");

        mockServer.expect(MockRestRequestMatchers.requestTo("https://api.paymentgateway.com/refunds"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withSuccess("{\"refundId\":\"refund123\",\"amount\":50.0,\"status\":\"SUCCESS\"}", MediaType.APPLICATION_JSON));

        // Act
        RefundResponse actualResponse = paymentGatewayClient.processRefund(transactionId, amount);

        // Assert
        assertEquals(expectedResponse.getStatus(), actualResponse.getStatus());
    }

    /**
     * Tests the creation of a payment when the payment gateway returns an error.
     * Mocks the payment gateway response to return a server error and verifies that a RuntimeException is thrown.
     */
    @Test
    void testCreatePayment_ApiError() {
        // Arrange
        PaymentRequest request = new PaymentRequest(100.0, "order123", "USD");

        mockServer.expect(MockRestRequestMatchers.requestTo("https://api.paymentgateway.com/payments"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withServerError());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            paymentGatewayClient.createPayment(request);
        });
    }

    /**
     * Tests the processing of a refund when the payment gateway returns an error.
     * Mocks the payment gateway response to return a server error and verifies that a RuntimeException is thrown.
     */
    @Test
    void testProcessRefund_ApiError() {
        // Arrange
        String transactionId = "txn123";
        Double amount = 50.0;

        mockServer.expect(MockRestRequestMatchers.requestTo("https://api.paymentgateway.com/refunds"))
                .andExpect(MockRestRequestMatchers.method(HttpMethod.POST))
                .andRespond(MockRestResponseCreators.withServerError());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> {
            paymentGatewayClient.processRefund(transactionId, amount);
        });
    }
}