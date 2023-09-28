package com.amnil.invbackend.dto;

import com.amnil.invbackend.entity.Product;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.*;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto {

    private Long id;

    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    //private Set<Product> product;
}
