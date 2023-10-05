package com.amnil.invbackend.dto.core;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * The type Product dto.
 */
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    /**
     * productId
     */
    private Long productId;
    /**
     * productName
     */
    private String productName;
    /**
     * productPrice
     */
    private double productPrice;
    /**
     * productQuantity
     */
    private Integer productQuantity;
    /**
     * supplier
     */
    private SupplierDto supplier;
}