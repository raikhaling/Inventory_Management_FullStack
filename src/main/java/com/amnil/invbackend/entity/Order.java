package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;

    @Column(name = "order_amount", nullable = false)
    private String orderAmount;

    @Column(name = "order_quantity", nullable = false)
    private String orderQuantity;

    @Column(name = "billing_address", nullable = false)
    private String billingAddress;

    @OneToOne
    private LocalUser user;

    @OneToMany(mappedBy = "order", cascade = {CascadeType.MERGE, CascadeType.PERSIST})
    private Set<OrderItem> orderItems = new HashSet<>();

}