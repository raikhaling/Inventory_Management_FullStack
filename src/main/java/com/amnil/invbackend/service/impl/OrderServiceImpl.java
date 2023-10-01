package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.OrderItemRequest;
import com.amnil.invbackend.dto.PlaceOrderRequestDto;
import com.amnil.invbackend.dto.core.OrderDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Order;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.repository.OrderRepository;
import com.amnil.invbackend.repository.ProductRepository;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.OrderService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
@Slf4j
public class OrderServiceImpl implements OrderService {
    private final OrderRepository orderRepository;
    private final OrderItemRepository orderItemRepository;
    private final UserRepository userRepository;
    private final ProductRepository productRepository;
    private final ModelMapper modelMapper;


    @Override
    public OrderDto placeOrder(PlaceOrderRequestDto request) {

        LocalUser user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + request.getUserId()));

        // Create an Order
        Order order = new Order();
        order.setBillingAddress(request.getBillingAddress());
        order.setUser(user);

        Set<OrderItem> orderItems = new HashSet<>();

        // Process and associate order items with products
        for (OrderItemRequest itemRequest : request.getOrderItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setQuantity(itemRequest.getQuantity());

            // find the product by product ID from the request
            Product product = productRepository.findById(itemRequest.getProductId())
                    .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + itemRequest.getProductId()));

            orderItem.setProduct(product);
            orderItem.setOrder(order);

            orderItems.add(orderItem);
        }
        order.setOrderItems(orderItems);

        // calculate the total order amount
        double totalAmount = orderItems.stream()
                .mapToDouble(item -> item.getProduct().getProductPrice() * item.getQuantity())
                .sum();
        order.setOrderAmount(String.valueOf(totalAmount));

        Long totalQty = orderItems.stream()
                .mapToLong(OrderItem::getQuantity)
                .sum();

        order.setOrderQuantity(totalQty);

        Order savedOrder = orderRepository.save(order);
        List<OrderItem> savedOrderItems = orderItemRepository.saveAll(orderItems);


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

//    public OrderDto createOrder( Long orderItemId,  OrderDto orderDto) {
//
//        Optional<OrderItem> orderItemOptional = orderItemRepository.findById(orderItemId);
//
//        OrderItem orderItem;
//
//        if (orderItemOptional.isPresent()) {
//            orderItem = orderItemOptional.get();
//        } else {
//            OrderItem newOrderItem = new OrderItem();
//            newOrderItem.setProduct(null); // Set the product
//            orderItem = orderItemRepository.save(newOrderItem);
//        }
//
//
//        // Find the user by user ID from the orderDto
//        LocalUser user = userRepository.findById(orderDto.getUserId())
//                .orElseThrow(() -> new EntityNotFoundException("User not found with id: " + orderDto.getUserId()));
//        log.info("User found.");
//        Order order = modelMapper.map(orderDto, Order.class);
//        order.setUser(user);
//        order.setOrderAmount(orderDto.getOrderAmount());
//        order.setOrderQuantity(orderDto.getOrderQuantity());
//        order.setBillingAddress(order.getBillingAddress());
//
//        //orderItem.setOrder(order);
//
//
//        Order savedOrder = orderRepository.save(order);
//        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//
//        return modelMapper.map(savedOrder, OrderDto.class);
//    }





}
