package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.dto.core.SupplierDto;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.entity.Supplier;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.ProductRepository;
import com.amnil.invbackend.repository.SupplierRepository;
import com.amnil.invbackend.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Product service.
 */
@Service
@RequiredArgsConstructor
@Slf4j
public class ProductServiceImpl implements ProductService {
    /**
     * productRepository
     */
    private final ProductRepository productRepository;
    /**
     * supplierRepository
     */
    private final SupplierRepository supplierRepository;
    /**
     * modelMapper
     */
    private final ModelMapper modelMapper;

    @Override
    public ProductDto getProductById(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Product not found with id: " + id));
        return modelMapper.map(product, ProductDto.class);
    }

    @Override
    public List<ProductDto> getAllProducts() {
        List<Product> products = productRepository.findAll();
        List<ProductDto> productDtos = products.stream()
                .map(product -> modelMapper.map(product, ProductDto.class))
                .collect(Collectors.toList());
        return productDtos;
    }
//for future thoughts
//    @Override
//    public ProductDto saveProduct(ProductDto productDto) {
//        Product product = modelMapper.map(productDto,Product.class);
//        productRepository.save(product);
//        return modelMapper.map(product,ProductDto.class);
//    }

    @Override
    public ProductDto saveProduct(ProductDto productDto, Long supplierId) {
        //fetch supplier is there or not
        log.info("supplier searching");
        Supplier supplier = supplierRepository.findById(supplierId)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + supplierId));
        log.info("supplier found");
        Product product = modelMapper.map(productDto, Product.class);
        product.setSupplier(supplier);

        productRepository.save(product);
        return modelMapper.map(product, ProductDto.class);
    }



    @Override
    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    @Override
    public List<ProductDto> getAllProductsBySupplier(Long id) {
        Supplier supplier = supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not Found with id: " + id));
        List<Product> products = productRepository.findBySupplier(supplier);

        if (products.isEmpty())
            throw new EntityNotFoundException("List is empty.");
        return products.stream()
                .map((element) -> modelMapper.map(element, ProductDto.class))
                .toList();
    }

    @Override
    public List<ProductDto> searchProductContaining(String key) {
//        log.info("fetching products from service..");
//        List<Product> searchedProducts = productRepository.searchProduct(key);
//        log.info("repo called successfully");
//        if(searchedProducts.isEmpty()){
//            throw new EntityNotFoundException("No product matched.");
//        }
//        else {
//            return searchedProducts.stream()
//                    .map((element) -> modelMapper.map(element, ProductDto.class)).toList();
//
            try {//List<Product> products = productRepository.findByProductName("%" + key + "%");
                List<Product> products = productRepository.findByProductNameContaining(key);
                System.out.println(products);
                return products.stream().map((element) -> modelMapper.map(element, ProductDto.class)).toList();


            }catch (Exception e){
                throw new EntityNotFoundException("Exception occurred during product searching.");
            }

    }

    @Override
    public List<ProductDto> searchProductStarting(String key) {

        try {
            List<Product> products = productRepository.findByProductNameStartingWith(key);
            return products.stream().map((element) -> modelMapper.map(element, ProductDto.class)).toList();
        }catch (Exception e){
            throw new EntityNotFoundException("Nothing matched.");
        }
    }

    @Override
    public List<ProductDto> searchProductNatively(String key) {
        try {
            List<Product> productList = productRepository.searchProductNative(key);
            return productList.stream().map((element) -> modelMapper.map(element, ProductDto.class)).toList();
        }catch (Exception e){
            throw new EntityNotFoundException("No matched found");
        }
    }

    //Custom mapper function
//    public ProductDto toDto(Product product){
//        ProductDto productDto = new ProductDto();
//        productDto.setProductId(product.getId());
//        productDto.setProductDescription(product.getProductDescription());
//        productDto.setProductImage(product.getProductImage());
//        productDto.setProductPrice(product.getProductPrice());
//
//        //change supplier to supplierDto
//        SupplierDto supplierDto = new SupplierDto();
//        supplierDto.setSupplierId(product.getSupplier().getId());
//        supplierDto.setName(product.getSupplier().getName());
//        supplierDto.setContactEmail(product.getSupplier().getContactEmail());
//        productDto.setSupplier(supplierDto);
//        return productDto;
//    }
} /**
 * log
 */ /**
 * log
 */


