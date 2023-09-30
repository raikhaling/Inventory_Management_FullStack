package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.ProductDto;

import java.util.List;

public interface ProductService {
    ProductDto getProductById(Long id);
    List<ProductDto> getAllProducts();
  //  ProductDto saveProduct(ProductDto product);
    void deleteProduct(Long id);
    List<ProductDto> getAllProductsBySupplier(Long id);
    ProductDto saveProduct(ProductDto productDto, Long supplierId);
}
