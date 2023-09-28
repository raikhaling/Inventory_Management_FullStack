package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {
    List<Product> findBySupplier(Supplier supplier);
}
