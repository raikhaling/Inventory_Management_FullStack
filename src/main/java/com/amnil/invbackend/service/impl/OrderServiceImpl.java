package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.OrderDto;
import com.amnil.invbackend.dto.OrderItemDto;
import com.amnil.invbackend.dto.UserDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Order;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.repository.OrderRepository;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ModelMapper modelMapper;


    public OrderDto createOrder( Long orderItemId,  OrderDto orderDto) {

        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);

        OrderItem orderItem;

        if (orderItemOptional.isPresent()) {
             orderItem = orderItemOptional.get();
        } else {
            OrderItem newOrderItem = new OrderItem();
            newOrderItem.setProduct(null); // Set the product
            orderItem = orderItemRepository.save(newOrderItem);
        }


        // Find the user by user ID from the orderDto
        LocalUser user = userRepository.findById(orderDto.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDto.getUserId()));
        log.info("User found.");
        Order order = modelMapper.map(orderDto, Order.class);
        order.setUser(user);
        order.setOrderAmount(orderDto.getOrderAmount());
        order.setOrderQuantity(orderDto.getOrderQuantity());
        order.setBillingAddress(order.getBillingAddress());

        //orderItem.setOrder(order);


        Order savedOrder = orderRepository.save(order);
        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return modelMapper.map(savedOrder, OrderDto.class);
    }



//    public OrderDto createOrder(OrderDto orderDto) {
//        LocalUser user = userRepository.findById(orderDto.getUserId())
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDto.getUserId()));
//
//        Order order = modelMapper.map(orderDto, Order.class);
//
//        order.setUser(user);
//
//        Set<OrderItem> orderItems = new HashSet<>();
//        for (OrderItemDto itemDto : orderDto.getOrderItems()) {
//            // Map each OrderItemDto to an OrderItem entity
//            OrderItem orderItem = modelMapper.map(itemDto, OrderItem.class);
//
//            orderItem.setOrder(order);
//
//            orderItems.add(orderItem);
//        }
//
//        order.setOrderItems(orderItems);
//
//        Order savedOrder = orderRepository.save(order);
//
//        return modelMapper.map(savedOrder, OrderDto.class);
//    }

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
