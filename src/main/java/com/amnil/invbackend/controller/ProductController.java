package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
   // @PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/public/products/{id}")
    public ResponseEntity<ProductDto> getProductById(@PathVariable Long id){
        ProductDto productDto = productService.getProductById(id);
        if(productDto != null)
            return ResponseEntity.ok(productDto);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/public/products")
    public ResponseEntity<List<ProductDto>> getProducts(){
        List<ProductDto> products = productService.getAllProducts();
        if(!products.isEmpty())
            return ResponseEntity.ok(products);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //create
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/products-by/{supplierId}")
    public ResponseEntity<ProductDto> createProduct( @RequestBody ProductDto productDto,
                                                     @PathVariable Long supplierId){
        ProductDto savedProduct = productService.saveProduct(productDto, supplierId);
        return ResponseEntity.ok(savedProduct);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> updateOrder(@PathVariable Long id,
                                               @RequestBody ProductDto updateProduct){

        ProductDto existingProduct = productService.getProductById(id);
        if(existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        updateProduct.setProductId(id);
      //  productService.saveProduct(updateProduct);
        return ResponseEntity.ok(updateProduct);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/products/{id}")
    public ResponseEntity<ProductDto> deleteOrder(@PathVariable Long id){
        ProductDto existingOrder = productService.getProductById(id);
        if(existingOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            productService.deleteProduct(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }
    //Find Product by Suppliers
    @GetMapping("/public/find-products-by-supplier/{supplierId}")
    public ResponseEntity<List<ProductDto>> getProductsBySuppliers(
            @PathVariable Long supplierId){
        List<ProductDto> productsBySupplier = productService.getAllProductsBySupplier(supplierId);
        return new ResponseEntity<>(productsBySupplier,HttpStatus.OK);
    }
}
