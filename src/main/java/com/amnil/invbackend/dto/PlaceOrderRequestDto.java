package com.amnil.invbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * The type Place order request dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequestDto {
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
    private List<OrderItemRequest> orderItems;

}
