package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.OrderItemDto;

import java.util.List;

/**
 * The interface Order item service.
 */
public interface OrderItemService {
    /**
     * Create order item order item dto.
     *
     * @param orderItemDto the order item dto
     * @return the order item dto
     */
    OrderItemDto createOrderItem(OrderItemDto orderItemDto);

    /**
     * Gets all order items.
     *
     * @return the all order items
     */
    List<OrderItemDto> getAllOrderItems();

    /**
     * Gets order item by id.
     *
     * @param id the id
     * @return the order item by id
     */
    List<OrderItemDto> getOrderItemById(Long id);

    /**
     * Update order item order item dto.
     *
     * @param id                  the id
     * @param updatedOrderItemDto the updated order item dto
     * @return the order item dto
     */
    OrderItemDto updateOrderItem(Long id, OrderItemDto updatedOrderItemDto);

    /**
     * Delete order item.
     *
     * @param id the id
     */
    void deleteOrderItem(Long id);
}
