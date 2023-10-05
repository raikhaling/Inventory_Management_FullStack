package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrderRepository extends JpaRepository<Order,Long> {

}
