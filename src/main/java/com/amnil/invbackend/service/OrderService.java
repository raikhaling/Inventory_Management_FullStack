package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.OrderDto;


import java.util.List;

public interface OrderService {
    OrderDto createOrder(OrderDto orderDto);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, OrderDto updatedOrderDto);

    void deleteOrder(Long id);
}
