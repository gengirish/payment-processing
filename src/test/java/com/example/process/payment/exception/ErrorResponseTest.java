package com.example.process.payment.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit tests for the ErrorResponse class.
 */
public class ErrorResponseTest {

    /**
     * Tests the ErrorResponse constructor and getters.
     */
    @Test
    public void testErrorResponse() {
        ErrorResponse errorResponse = new ErrorResponse(404, "Not Found");

        assertEquals(404, errorResponse.getStatus());
        assertEquals("Not Found", errorResponse.getMessage());
    }

    @Test
    public void testErrorResponseSetterGetter() {
        ErrorResponse errorResponse = new ErrorResponse(404, "Not Found");
        errorResponse.setStatus(500);
        errorResponse.setMessage("new");

        assertEquals(500, errorResponse.getStatus());
        assertEquals("new", errorResponse.getMessage());
    }
}