package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.OrderDto;
import com.amnil.invbackend.dto.OrderItemDto;
import com.amnil.invbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    private final OrderService orderService;

    @PostMapping("/admin/order/{orderItemId}")
    public ResponseEntity<OrderDto> createOrder(@PathVariable Long orderItemId, @RequestBody OrderDto orderDto) {
       log.info("inside oder create controller.");
        OrderDto createdOrder = orderService.createOrder(orderItemId, orderDto);
        return ResponseEntity.ok(createdOrder);
    }


    @GetMapping("/order/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @GetMapping("/order")
    public ResponseEntity<List<OrderDto>> getAllOrders() {
       List<OrderDto> orders = orderService.getAllOrders();
       if (orders.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(orders);
    }

    @PutMapping("/order/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto updatedOrderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, updatedOrderDto);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

}