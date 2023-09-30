package com.amnil.invbackend.dto.core;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private Long supplierId;

    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    //private Set<Product> product;
}
