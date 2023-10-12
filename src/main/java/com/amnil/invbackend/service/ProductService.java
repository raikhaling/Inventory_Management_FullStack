package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.ProductDto;

import java.util.List;

/**
 * The interface Product service.
 */
public interface ProductService {
    /**
     * Gets product by id.
     *
     * @param id the id
     * @return the product by id
     */
    ProductDto getProductById(Long id);

    /**
     * Gets all products.
     *
     * @return the all products
     */
    List<ProductDto> getAllProducts();

    /**
     * Delete product.
     *
     * @param id the id
     */
//  ProductDto saveProduct(ProductDto product);
    void deleteProduct(Long id);

    /**
     * Gets all products by supplier.
     *
     * @param id the id
     * @return the all products by supplier
     */
    List<ProductDto> getAllProductsBySupplier(Long id);

    /**
     * Gets all product pageable.
     *
     * @param key  the key
     * @param page the page
     * @param size the size
     * @return the all product pageable
     */
    List<ProductDto> getAllProductPageable(String key, int page, int size);

    /**
     * Save product product dto.
     *
     * @param productDto the product dto
     * @param supplierId the supplier id
     * @return the product dto
     */
    ProductDto saveProduct(ProductDto productDto, Long supplierId);

    /**
     * Search product list.
     *
     * @param key the key
     * @return the list
     */
    List<ProductDto> searchProductContaining(String key);

    /**
     * Search product starting list.
     *
     * @param key the key
     * @return the list
     */
    List<ProductDto> searchProductStarting(String key);

    /**
     * Search product natively list.
     *
     * @param key the key
     * @return the list
     */
    List<ProductDto> searchProductNatively(String key);
}
