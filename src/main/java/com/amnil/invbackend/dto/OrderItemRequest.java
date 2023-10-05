package com.amnil.invbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Order item request.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemRequest {
    /**
     * productId
     */
    private Long productId;
    /**
     * quantity
     */
    private int quantity;
}
