package com.amnil.invbackend.dto.core;

import lombok.*;
import org.springframework.hateoas.RepresentationModel;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class SupplierDto extends RepresentationModel<SupplierDto> {

    private Long supplierId;

    private String name;

    private String contactName;

    private String contactEmail;

    private String contactPhone;

    //private Set<Product> product;
}
