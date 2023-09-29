package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.OrderItemDto;
import com.amnil.invbackend.entity.LocalUser;
import com.amnil.invbackend.entity.Order;
import com.amnil.invbackend.entity.OrderItem;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.repository.OrderRepository;
import com.amnil.invbackend.repository.ProductRepository;
import com.amnil.invbackend.repository.UserRepository;
import com.amnil.invbackend.service.OrderItemService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class OrderItemServiceImpl implements OrderItemService {
    private final UserRepository userRepository;
    private final OrderRepository orderRepository;
    private final ProductRepository productRepository;
    private final OrderItemRepository orderItemRepository;
    private final ModelMapper modelMapper;


    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
        //left to complete
        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
        log.info("mapped successfully");

        Product product = productRepository.findById(orderItemDto.getProductId())
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: "+orderItemDto.getProductId()));
        log.info("product found.");

        Optional<Order> optionalOrder = orderRepository.findById(orderItemDto.getOrderId());
        if (optionalOrder.isPresent()) {
            Order order = optionalOrder.get();
            orderItem.setOrder(order);
        } else {
            // No order exists, so create a new one
            Order newOrder = new Order();

            // Set the user for the new order based on orderItemDto
            LocalUser user = userRepository.findById(orderItemDto.getId())
                    .orElseThrow(() -> new EntityNotFoundException("User " +
                            "not found with id: " + orderItemDto.getId()));
            newOrder.setUser(user);
            newOrder.setOrderAmount("0.0"); // Set default values as needed
            newOrder.setOrderQuantity("0");
            newOrder.setBillingAddress("");

            Order savedOrder = orderRepository.save(newOrder);

            orderItem.setOrder(savedOrder);
        }

        orderItem.setProduct(product);


        log.info("starting to save");

        OrderItem savedOrderItem = orderItemRepository.save(orderItem);

        return modelMapper.map(savedOrderItem, OrderItemDto.class);
    }
    //    public OrderItemDto createOrderItem(OrderItemDto orderItemDto) {
//
//        OrderItem orderItem = modelMapper.map(orderItemDto, OrderItem.class);
//        log.info("Mapped successfully");
//
//        Product product = productRepository.findById(orderItemDto.getProductId())
//                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + orderItemDto.getProductId()));
//        log.info("Product found.");
//
//        // Set the product for the order item
//        orderItem.setProduct(product);
//        orderItem.setQuantity(orderItemDto.getQuantity());
//
//        log.info("Starting to save");
//
//        OrderItem savedOrderItem = orderItemRepository.save(orderItem);
//
//        return modelMapper.map(savedOrderItem, OrderItemDto.class);
//    }


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
