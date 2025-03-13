package com.example.process.payment.exception;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class GlobalExceptionHandlerTest {

    private final GlobalExceptionHandler handler = new GlobalExceptionHandler();

    @Test
    public void testHandlePaymentNotFoundException() {
        PaymentNotFoundException ex = new PaymentNotFoundException("Payment not found");
        ResponseEntity<ErrorResponse> response = handler.handlePaymentNotFoundException(ex);
        assertEquals(HttpStatus.NOT_FOUND, response.getStatusCode());
        assertEquals("Payment not found", response.getBody().getMessage());
    }

    @Test
    public void testHandleInvalidPaymentRequestException() {
        InvalidPaymentRequestException ex = new InvalidPaymentRequestException("Invalid request");
        ResponseEntity<ErrorResponse> response = handler.handleInvalidPaymentRequestException(ex);
        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertEquals("Invalid request", response.getBody().getMessage());
    }

    @Test
    public void testHandleGeneralException() {
        Exception ex = new Exception("General error");
        ResponseEntity<ErrorResponse> response = handler.handleGeneralException(ex);
        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("An unexpected error occurred.", response.getBody().getMessage());
    }
}