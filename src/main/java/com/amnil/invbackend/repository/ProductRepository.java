package com.amnil.invbackend.repository;

import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.entity.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

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


    /**
     * Search product native list.
     *
     * @param key the key
     * @return the list
     */
    @Query(value = "select * from product p where p.product_name like %:key%", nativeQuery = true)
    List<Product> searchProductNative(@Param("key") String key);

    /**
     * Find by product name list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByProductName(String name);

    /**
     * Find by product name containing list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByProductNameContaining(String name);

    /**
     * Find by product name starting with list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByProductNameStartingWith(String name);


}
