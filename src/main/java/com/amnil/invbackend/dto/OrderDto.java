package com.amnil.invbackend.dto;

import lombok.Data;

import java.util.Set;

@Data
public class OrderDto {
    private Long id;
    private String orderAmount;
    private String orderQuantity;
    private String billingAddress;
    private Long userId;
    private Set<OrderItemDto> orderItems;
}
