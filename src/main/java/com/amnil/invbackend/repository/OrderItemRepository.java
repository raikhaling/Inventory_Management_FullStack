package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Order item repository.
 */
public interface OrderItemRepository extends JpaRepository<OrderItem, Long> {
}
