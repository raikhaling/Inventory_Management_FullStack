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
     * Search product list.
     *
     * @param key the key
     * @return the list
     */
//    @Query("select p from Product p where p.product_name like concat(:key, '%') order by p.name asc")
//    List<Product> searchProduct(@Param("key") String key);

    /**
     * Find by product name list.
     *
     * @param name the name
     * @return the list
     */
    List<Product> findByProductName(String name);

    List<Product> findByProductNameContaining(String name);

}
