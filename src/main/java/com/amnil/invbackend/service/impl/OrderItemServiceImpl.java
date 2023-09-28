package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.OrderItemDto;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderItemServiceImpl implements OrderItemService {
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;
    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        //left to complete
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);

        // Set the order and product (you can fetch them based on orderId and productId)

        // Order order = orderRepository.findById(orderItemDto.getOrderId()).orElse(null);
        //          Product product = productRepository.findById(orderItemDto.getProductId()).orElse(null);
        //          orderItem.setOrder(order);
        //          orderItem.setProduct(product);


        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return modelMapper.map(savedOrderItem, OrderItemDto.class);
    }

    public List<OrderItemDto> getAllOrderItems() {
        List<OrderItem> orderItems = orderItemRepository.findAll();
        return orderItems.stream()
                .map(orderItem -> modelMapper.map(orderItem, OrderItemDto.class))
                .collect(Collectors.toList());
    }

    public OrderItemDto getOrderItemById(Long id) {
        OrderItem orderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found with id: " + id));
        return modelMapper.map(orderItem, OrderItemDto.class);
    }

    public OrderItemDto updateOrderItem(Long id, OrderItemDto updatedOrderItemDto) {
        // Check if the order item
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found with id: " + id));

        modelMapper.map(updatedOrderItemDto, existingOrderItem);

        OrderItem updatedOrderItem = orderItemRepository.save(existingOrderItem);
        return modelMapper.map(updatedOrderItem, OrderItemDto.class);
    }

    public void deleteOrderItem(Long id) {
        OrderItem existingOrderItem = orderItemRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order Item not found with id: " + id));
        orderItemRepository.delete(existingOrderItem);
    }
}
