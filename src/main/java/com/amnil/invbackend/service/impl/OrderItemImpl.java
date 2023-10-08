package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.core.OrderItemDto;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderItemImpl implements OrderItemService {

    /**
     * orderItemRepository
     */
    private final OrderItemRepository orderItemRepository;
    /**
     * modelMapper
     */
    private final ModelMapper modelMapper;

    @Override
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        return null;
    }

    @Override
    public List<OrderItemDto> getAllOrderItems() {
        return null;
    }

    @Override
    public List<OrderItemDto> getOrderItemById(Long id) {

//        OrderItem orderItem = orderItemRepository.findById(id)
//                .orElseThrow(() -> new EntityNotFoundException("Order-item not found with id: "+id));
        List<OrderItem> orderItems = orderItemRepository.findByOrderId(id);

        return orderItems.stream()
                .map((element) -> modelMapper.map(element, OrderItemDto.class)).toList();
    }

    @Override
    public OrderItemDto updateOrderItem(Long id, OrderItemDto updatedOrderItemDto) {
        return null;
    }

    @Override
    public void deleteOrderItem(Long id) {

    }
}
