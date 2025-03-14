package com.example.process.payment.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

/**
 * Entity class representing a payment.
 * This class maps to the "payments" table in the database.
 */
@Entity
@Table(name = "payments")
public class Payment {

    /**
     * The unique identifier for the payment.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * The transaction ID associated with the payment.
     */
    private String transactionId;

    /**
     * The status of the payment.
     */
    private String status;

    /**
     * The amount of the payment.
     */
    private Double amount;

    /**
     * The currency of the payment.
     */
    private String currency;

    /**
     * The email of the customer making the payment.
     */
    private String customerEmail;

    /**
     * The provider of the payment service.
     */
    private String provider;

    /**
     * The URL for the payment.
     */
    private String paymentUrl;

    /**
     * The external payment ID provided by the payment gateway.
     */
    private String externalPaymentId;

    /**
     * The timestamp when the payment was created.
     */
    @CreationTimestamp
    private LocalDateTime createdAt;

    /**
     * The timestamp when the payment was last updated.
     */
    @UpdateTimestamp
    private LocalDateTime updatedAt;

    // Getters and setters...

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Double getAmount() {
        return amount;
    }

    public void setAmount(Double amount) {
        this.amount = amount;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public String getCustomerEmail() {
        return customerEmail;
    }

    public void setCustomerEmail(String customerEmail) {
        this.customerEmail = customerEmail;
    }

    public String getProvider() {
        return provider;
    }

    public void setProvider(String provider) {
        this.provider = provider;
    }

    public String getPaymentUrl() {
        return paymentUrl;
    }

    public void setPaymentUrl(String paymentUrl) {
        this.paymentUrl = paymentUrl;
    }

    public String getExternalPaymentId() {
        return externalPaymentId;
    }

    public void setExternalPaymentId(String externalPaymentId) {
        this.externalPaymentId = externalPaymentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }
}