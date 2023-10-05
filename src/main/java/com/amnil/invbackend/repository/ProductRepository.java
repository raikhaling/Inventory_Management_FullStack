package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * The interface Product repository.
 */
public interface ProductRepository extends JpaRepository<Product, Long> {
    /**
     * Find by supplier list.
     *
     * @param supplier the supplier
     * @return the list
     */
    List<Product> findBySupplier(Supplier supplier);
}
