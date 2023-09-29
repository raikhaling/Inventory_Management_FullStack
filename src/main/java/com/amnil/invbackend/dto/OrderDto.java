package com.amnil.invbackend.dto;

import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.OrderItem;
import jakarta.persistence.CascadeType;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class OrderDto {
    private Long id;
    private String orderAmount;
    private String orderQuantity;
    private String billingAddress;
    private Long userId;
    private Set<OrderItemDto> orderItems;
}
