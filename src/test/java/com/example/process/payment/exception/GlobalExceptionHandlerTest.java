package com.example.process.payment.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the GlobalExceptionHandler class.
 */
public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    /**
     * Tests the handlePaymentNotFoundException method.
     * Verifies that the correct response is returned for a PaymentNotFoundException.
     */
    @Test
    public void testHandlePaymentNotFoundException() {
        PaymentNotFoundException ex = new PaymentNotFoundException("Payment not found");
        ResponseEntity<ErrorResponse> response = handler.handlePaymentNotFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Payment not found", response.getBody().getMessage());
    }

    /**
     * Tests the handleInvalidPaymentRequestException method.
     * Verifies that the correct response is returned for an InvalidPaymentRequestException.
     */
    @Test
    public void testHandleInvalidPaymentRequestException() {
        InvalidPaymentRequestException ex = new InvalidPaymentRequestException("Invalid request");
        ResponseEntity<ErrorResponse> response = handler.handleInvalidPaymentRequestException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody().getMessage());
    }

    /**
     * Tests the handleGeneralException method.
     * Verifies that the correct response is returned for a general Exception.
     */
    @Test
    public void testHandleGeneralException() {
        Exception ex = new Exception("General error");
        ResponseEntity<ErrorResponse> response = handler.handleGeneralException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody().getMessage());
    }
}