package com.amnil.invbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductDto {
    private Long id;
    private String productName;
    private double productPrice;
    private Boolean stock;
    private Integer productQuantity;
    private Boolean live;
    private String productImage;
    private String  productDescription;
    private SupplierDto supplier;
}
