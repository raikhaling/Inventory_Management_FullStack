package com.amnil.invbackend.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PlaceOrderRequestDto {
    private String orderAmount;
    private String orderQuantity;
    private String billingAddress;
    private Long userId;
    private List<OrderItemRequest> orderItems;

}
