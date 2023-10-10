package com.amnil.invbackend.dto.core;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.hateoas.RepresentationModel;

import java.util.Set;

/**
 * The type Order dto.
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto extends RepresentationModel<OrderDto> {

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
    @NotBlank(message = "Billing address is required")
    private String billingAddress;

    /**
     * userId
     */
    @NotNull(message = "User ID is required")
    private Long userId;

    /**
     * orderItems
     */
    private Set<OrderItemDto> orderItems;
}
