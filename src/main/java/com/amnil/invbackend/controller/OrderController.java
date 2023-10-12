package com.amnil.invbackend.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;
import com.amnil.invbackend.dto.PlaceOrderRequestDto;
import com.amnil.invbackend.dto.core.OrderDto;
import com.amnil.invbackend.dto.core.OrderItemDto;
import com.amnil.invbackend.repository.OrderItemRepository;
import com.amnil.invbackend.service.OrderItemService;
import com.amnil.invbackend.service.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Order controller.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class OrderController {
    /**
     * orderService
     */
    private final OrderService orderService;

    /**
     * orderItemService
     */
    private final OrderItemService orderItemService;


    /**
     * Place order response entity.
     *
     * @param request the request
     * @return the response entity
     */
    @PostMapping("/public/place-order")
    public ResponseEntity<OrderDto> placeOrder(@RequestBody PlaceOrderRequestDto request) {
        OrderDto placedOrder = orderService.placeOrder(request);
        return ResponseEntity.ok(placedOrder);
    }


    /**
     * Gets order by id.
     *
     * @param id the id
     * @return the order by id
     */
    @GetMapping("/public/order/{id}")
    public ResponseEntity<OrderDto> getOrderById(@PathVariable Long id) {
        OrderDto order = orderService.getOrderById(id);
        if (order != null) {
            return ResponseEntity.ok(order);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Gets all orders.
     *
     * @return the all orders
     */
    @GetMapping("/public/order")
    public ResponseEntity<List<OrderDto>> getAllOrders(@RequestParam(name = "page", defaultValue = "0") int page,
                                                       @RequestParam(name = "size", defaultValue = "10")int size) {
       List<OrderDto> orders = orderService.getAllOrders(page, size);

       orders.stream()
               .map((order) -> order.add(linkTo(methodOn(OrderController.class)
                       .getOrderItemById(order.getOrderId())).withSelfRel())).toList();

       if (orders.isEmpty()){
           return ResponseEntity.notFound().build();
       }
       return ResponseEntity.ok(orders);
    }

    /**
     * Update order response entity.
     *
     * @param id              the id
     * @param updatedOrderDto the updated order dto
     * @return the response entity
     */
    @PutMapping("/admin/order/{id}")
    public ResponseEntity<OrderDto> updateOrder(@PathVariable Long id, @RequestBody OrderDto updatedOrderDto) {
        OrderDto updatedOrder = orderService.updateOrder(id, updatedOrderDto);
        if (updatedOrder != null) {
            return ResponseEntity.ok(updatedOrder);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Delete order response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/admin/order/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        orderService.deleteOrder(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("public/order/{id}/order-items")
    public ResponseEntity<List<OrderItemDto>> getOrderItemById(@PathVariable Long id){
        List<OrderItemDto> orderItemDto =  orderItemService.getOrderItemById(id);
       if (orderItemDto != null){
           return ResponseEntity.ok(orderItemDto);
       }
       else
           return ResponseEntity.noContent().build();
    }


} /**
 * log
 */ /**
 * log
 */