package com.example.process.payment.repository;

import com.example.process.payment.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Repository interface for managing Payment entities.
 * Provides methods for performing CRUD operations on Payment entities.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    /**
     * Finds a payment by its transaction ID.
     *
     * @param transactionId the transaction ID of the payment
     * @return an Optional containing the payment if found, or empty if not found
     */
    Optional<Payment> findByTransactionId(String transactionId);
}