package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Positive;
import lombok.Getter;
import lombok.Setter;

/**
 * The type Order item.
 */
@Getter
@Setter
@Entity
@Table(name = "order_item")
public class OrderItem {
    /**
     * id
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id", nullable = false)
    private Long id;


    /**
     * quantity
     */
    private int quantity;

    /**
     * order
     */
    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    /**
     * product
     */
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

}
