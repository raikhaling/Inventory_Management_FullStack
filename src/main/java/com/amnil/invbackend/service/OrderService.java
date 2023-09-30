package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.PlaceOrderRequestDto;
import com.amnil.invbackend.dto.core.OrderDto;


import java.util.List;

public interface OrderService {
    OrderDto placeOrder(PlaceOrderRequestDto request);

    List<OrderDto> getAllOrders();

    OrderDto getOrderById(Long id);

    OrderDto updateOrder(Long id, OrderDto updatedOrderDto);

    void deleteOrder(Long id);
}
