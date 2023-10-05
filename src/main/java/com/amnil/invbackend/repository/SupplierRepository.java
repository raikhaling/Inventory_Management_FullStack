package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * The interface Supplier repository.
 */
public interface SupplierRepository extends JpaRepository<Supplier,Long> {
}
