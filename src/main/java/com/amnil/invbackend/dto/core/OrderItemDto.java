package com.amnil.invbackend.dto.core;

import com.amnil.invbackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    private Long orderItemId;
    private int quantity;
    private Long orderId;
    private ProductDto product;
}
