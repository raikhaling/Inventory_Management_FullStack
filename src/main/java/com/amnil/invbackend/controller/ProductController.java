package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Product controller.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
@Slf4j
public class ProductController {

    /**
     * productService
     */
    private final ProductService productService;

    /**
     * Get product by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto = productService.getProductById(id);
        if(productDto != null)
            return ResponseEntity.ok(productDto);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /**
     * Get products response entity.
     *
     * @return the response entity
     */
    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getAllProducts();
        if(!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /**
     * Create product response entity.
     *
     * @param productDto the product dto
     * @param supplierId the supplier id
     * @return the response entity
     */
    @PostMapping("/admin/products-by/{supplierId}")
    public ResponseEntity<ProductDto> createProduct( @RequestBody ProductDto productDto,
                                                     @PathVariable Long supplierId){
        ProductDto savedProduct = productService.saveProduct(productDto, supplierId);
        return ResponseEntity.ok(savedProduct);
    }

    /**
     * Update product response entity.
     *
     * @param id            the id
     * @param updateProduct the update product
     * @return the response entity
     */
    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> updateProduct(@PathVariable Long id,
                                               @RequestBody ProductDto updateProduct){

        ProductDto existingProduct = productService.getProductById(id);
        if(existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateProduct.setProductId(id);
      //  productService.saveProduct(updateProduct);
        return ResponseEntity.ok(updateProduct);
    }

    /**
     * Delete product response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> deleteProduct(@PathVariable Long id){
        log.info("fetching products from controller");
        ProductDto existingOrder = productService.getProductById(id);
        if(existingOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    /**
     * Get products by suppliers response entity.
     *
     * @param supplierId the supplier id
     * @return the response entity
     */
    @GetMapping("/public/find-products-by-supplier/{supplierId}")
    public ResponseEntity<List<ProductDto>> getProductsBySuppliers(
            @PathVariable Long supplierId){
        List<ProductDto> productsBySupplier = productService.getAllProductsBySupplier(supplierId);
        return new ResponseEntity<>(productsBySupplier,HttpStatus.OK);
    }

    /**
     * Search product response entity.
     *
     * @param key the key
     * @return the response entity
     */
    @GetMapping("/public/search/product/{key}")
    public ResponseEntity<List<ProductDto>> searchProduct (@PathVariable String key){
     //   List<ProductDto> productDtoList = productService.searchProduct(key);
        List<ProductDto> productDtoList = productService.searchProduct(key);
        return ResponseEntity.ok(productDtoList);
    }


//    @GetMapping("/products/search")
//    public ResponseEntity<List<Product>> searchProducts(@RequestParam String name) {
//        List<Product> products = .findByNameLike("%" + name + "%");
//        return ResponseEntity.ok(products);
//    }
}
