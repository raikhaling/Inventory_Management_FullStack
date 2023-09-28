package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.OrderItemDto;

import java.util.List;

public interface OrderItemService {
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    List<OrderItemDto> getAllOrderItems();

    OrderItemDto getOrderItemById(Long id);

    OrderItemDto updateOrderItem(Long id, OrderItemDto updatedOrderItemDto);

    void deleteOrderItem(Long id);
}
