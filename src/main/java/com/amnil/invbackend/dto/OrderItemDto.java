package com.amnil.invbackend.dto;

import lombok.Data;

@Data
public class OrderItemDto {
    private Long id;
    private int quantity;
    private Long orderId;
    private Long productId;
}
