package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Order;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

/**
 * The interface Order repository.
 */
public interface OrderRepository extends JpaRepository<Order,Long> {
    Page<Order> findAll(Pageable pageable);

}
