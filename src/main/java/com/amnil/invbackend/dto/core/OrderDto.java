package com.amnil.invbackend.dto.core;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * The type Order dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    /**
     * orderId
     */
    private Long orderId;
    /**
     * orderAmount
     */
    private String orderAmount;
    /**
     * orderQuantity
     */
    private String orderQuantity;
    /**
     * billingAddress
     */
    private String billingAddress;
    /**
     * userId
     */
    private Long userId;
    /**
     * orderItems
     */
    private Set<OrderItemDto> orderItems;
}
