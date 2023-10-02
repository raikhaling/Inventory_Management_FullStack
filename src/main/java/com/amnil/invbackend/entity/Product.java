package com.amnil.invbackend.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;
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

    @NotBlank(message = "Product name is required")
    @Size(min = 2, max = 100, message = "Product name must be between 2 and 100 characters")
    @Column(name = "product_name")
    private String productName;

    @NotNull(message = "Product price is required")
    @DecimalMin(value = "0.01", message = "Product price must be greater than 0.01")
    @Column(name = "product_price")
    private double productPrice;



    @Positive(message = "Product quantity must be a positive number")
    @NotNull(message = "Product quantity is required")
    @Column(name = "product_quantity")
    private Integer productQuantity;

    private Boolean stock;

    private Boolean live;

    @Column(name = "product_image")
    private String productImage;

    @Size(min = 10, max = 200, message
            = "Product Description must be between 10 and 200 characters")
    @Column(name = "product_description")
    private String  productDescription;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    @OneToMany(mappedBy = "product")
    private List<OrderItem> orderItems = new ArrayList<>();


}