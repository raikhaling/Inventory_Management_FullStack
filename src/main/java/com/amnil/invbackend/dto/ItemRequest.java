package com.amnil.invbackend.dto;

import lombok.Data;

/**
 * The type Item request.
 */
@Data
public class ItemRequest {
    /**
     * productId
     */
    private Long productId;
    /**
     * quantity
     */
    private Long quantity;
}
