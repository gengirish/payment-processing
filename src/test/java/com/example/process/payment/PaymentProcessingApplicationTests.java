package com.example.process.payment;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test class for {@link PaymentProcessingApplication}.
 * This class contains a smoke test to verify that the Spring application context loads successfully.
 *
 * @author Your Name
 * @version 1.0
 * @since 2023-10-01
 */
@SpringBootTest
class PaymentProcessingApplicationTests {

    /**
     * Tests that the application context loads successfully.
     * This test ensures that the Spring Boot application starts without any configuration issues.
     *
     * @param context the Spring application context, automatically injected by Spring Boot.
     */
    @Test
    void contextLoads(ApplicationContext context) {
        // Assert that the application context is not null, indicating that it loaded successfully.
        assertThat(context).isNotNull();
    }

    /**
     * Tests that the main method runs without throwing any exceptions.
     * This test ensures that the application can be started using the main method.
     */
    @Test
    void testMainMethod() {
        // Run the main method and ensure it does not throw any exceptions.
        PaymentProcessingApplication.main(new String[]{});
    }
}