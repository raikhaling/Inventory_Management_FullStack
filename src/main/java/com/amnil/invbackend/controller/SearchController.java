package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
@RequestMapping("/api/v1/search")
public class SearchController {

    private final ProductService productService;

    @GetMapping("/public/product-containing/{key}")
    public ResponseEntity<List<ProductDto>> searchProductContaining (@PathVariable String key){
        //   List<ProductDto> productDtoList = productService.searchProduct(key);
        List<ProductDto> productDtoList = productService.searchProductContaining(key);
        return ResponseEntity.ok(productDtoList);
    }
    @GetMapping("/public/product-starting/{key}")
    public ResponseEntity<List<ProductDto>> searchProductStarting (@PathVariable String key){
        List<ProductDto> productDtoList = productService.searchProductStarting(key);
        return ResponseEntity.ok(productDtoList);
    }


}
