package com.amnil.invbackend.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long orderId;
    private String orderAmount;
    private String orderQuantity;
    private String billingAddress;
    private Long userId;
    private Set<OrderItemDto> orderItems;
}
