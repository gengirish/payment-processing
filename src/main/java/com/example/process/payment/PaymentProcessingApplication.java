package com.example.process.payment;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Main class for the Payment Processing Application.
 * This class is responsible for bootstrapping the Spring Boot application.
 */
@SpringBootApplication
public class PaymentProcessingApplication {

    /**
     * Main method to start the Spring Boot application.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        SpringApplication.run(PaymentProcessingApplication.class, args);
    }
}