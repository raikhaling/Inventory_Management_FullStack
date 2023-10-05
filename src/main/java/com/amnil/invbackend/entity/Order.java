package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

/**
 * The type Order.
 */
@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id", nullable = false)
    private Long id;

    /**
     * orderAmount
     */
    @NotBlank(message = "Order amount is required")
    @Column(name = "order_amount", nullable = false)
    private String orderAmount;

    /**
     * orderQuantity
     */
    @Positive(message = "Order quantity must be a positive number ")
    @Column(name = "order_quantity", nullable = false)
    private Long orderQuantity;

    /**
     * billingAddress
     */
    @NotBlank(message = "Billing address is required")
    @Column(name = "billing_address", nullable = false)
    private String billingAddress;


    /**
     * user
     */
    @ManyToOne
    @JoinColumn(name = "user_id")
    private LocalUser user;

    /**
     * orderItems
     */
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<OrderItem> orderItems = new HashSet<>();

}