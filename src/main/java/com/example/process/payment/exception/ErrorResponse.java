package com.example.process.payment.exception;

/**
 * Represents an error response with a status code and message.
 */
public class ErrorResponse {
    /**
     * The HTTP status code of the error.
     */
    private int status;

    /**
     * The error message.
     */
    private String message;

    /**
     * Constructs a new ErrorResponse with the specified status and message.
     *
     * @param status  the HTTP status code
     * @param message the error message
     */
    public ErrorResponse(int status, String message) {
        this.status = status;
        this.message = message;
    }

    /**
     * Gets the HTTP status code of the error.
     *
     * @return the HTTP status code
     */
    public int getStatus() {
        return status;
    }

    /**
     * Sets the HTTP status code of the error.
     *
     * @param status the HTTP status code
     */
    public void setStatus(int status) {
        this.status = status;
    }

    /**
     * Gets the error message.
     *
     * @return the error message
     */
    public String getMessage() {
        return message;
    }

    /**
     * Sets the error message.
     *
     * @param message the error message
     */
    public void setMessage(String message) {
        this.message = message;
    }
}