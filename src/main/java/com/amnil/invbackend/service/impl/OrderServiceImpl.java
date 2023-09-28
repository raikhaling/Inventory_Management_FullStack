package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.OrderDto;
import com.amnil.invbackend.dto.OrderItemDto;
import com.amnil.invbackend.dto.UserDto;
import com.amnil.invbackend.entity.Order;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.repository.OrderRepository;
import com.amnil.invbackend.service.OrderService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;

    public OrderDto createOrder(OrderDto orderDto) {

        Order order = modelMapper.map(orderDto, Order.class);

        // Set user (you can fetch the user based on userId)

        Set<OrderItem> orderItems = new HashSet<>();
        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
            OrderItem orderItem = modelMapper.map(itemDto, OrderItem.class);

            orderItems.add(orderItem);
        }

        order.setOrderItems(orderItems);
        Order savedOrder = orderRepository.save(order);
        return modelMapper.map(savedOrder, OrderDto.class);
    }

    public List<OrderDto> getAllOrders() {
        List<Order> orders = orderRepository.findAll();
        return orders.stream()
                .map(order -> modelMapper.map(order, OrderDto.class))
                .collect(Collectors.toList());
    }

    public OrderDto getOrderById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        return modelMapper.map(order, OrderDto.class);
    }

    public OrderDto updateOrder(Long id, OrderDto updatedOrderDto) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));

        modelMapper.map(updatedOrderDto, existingOrder);
        Order updatedOrder = orderRepository.save(existingOrder);
        return modelMapper.map(updatedOrder, OrderDto.class);
    }

    public void deleteOrder(Long id) {
        Order existingOrder = orderRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Order not found with id: " + id));
        orderRepository.delete(existingOrder);
    }




}
