package com.amnil.invbackend.dto.core;

import com.amnil.invbackend.entity.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Order item dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDto {
    /**
     * orderItemId
     */
    private Long orderItemId;
    /**
     * quantity
     */
    private int quantity;
    /**
     * orderId
     */
    private Long orderId;
    /**
     * product
     */
    private ProductDto product;
}
