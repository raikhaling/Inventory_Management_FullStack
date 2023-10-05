package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.PlaceOrderRequestDto;
import com.amnil.invbackend.dto.core.OrderDto;


import java.util.List;

/**
 * The interface Order service.
 */
public interface OrderService {
    /**
     * Place order order dto.
     *
     * @param request the request
     * @return the order dto
     */
    OrderDto placeOrder(PlaceOrderRequestDto request);

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    List<OrderDto> getAllOrders();

    /**
     * Gets order by id.
     *
     * @param id the id
     * @return the order by id
     */
    OrderDto getOrderById(Long id);

    /**
     * Update order order dto.
     *
     * @param id              the id
     * @param updatedOrderDto the updated order dto
     * @return the order dto
     */
    OrderDto updateOrder(Long id, OrderDto updatedOrderDto);

    /**
     * Delete order.
     *
     * @param id the id
     */
    void deleteOrder(Long id);
}
