package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "product_id", nullable = false)
    private Long id;

    @Column(name = "product_name")
    private String productName;

    @Column(name = "product_price")
    private double productPrice;

    @Column(name = "stock")
    private Boolean stock;

    @Column(name = "product_quantity")
    private Integer productQuantity;

    @Column(name = "live")
    private Boolean live;

    @Column(name = "product_image")
    private String productImage;

    @Column(name = "product_description")
    private String  productDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();


}