package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.ApiResponse;
import com.amnil.invbackend.dto.SupplierDto;
import com.amnil.invbackend.repository.SupplierRepository;
import com.amnil.invbackend.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SupplierController {
    private final SupplierService supplierService;
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/public/suppliers/{id}")
    public ResponseEntity<SupplierDto> getProductById(@PathVariable Long id){
        SupplierDto supplierDto = supplierService.getSupplierById(id);
        if(supplierDto != null)
            return ResponseEntity.ok(supplierDto);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //@PreAuthorize("hasAnyRole('ADMIN','USER')")
    @GetMapping("/public/suppliers")
    public ResponseEntity<List<SupplierDto>> getProducts(){
        List<SupplierDto> suppliers = supplierService.getSuppliers();
        if(!suppliers.isEmpty())
            return ResponseEntity.ok(suppliers);
        else
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/admin/suppliers")
    public ResponseEntity<SupplierDto> createProduct( @RequestBody SupplierDto supplierDto){
        SupplierDto supplierDto1 = supplierService.save(supplierDto);
        return ResponseEntity.ok(supplierDto1);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/admin/suppliers/{id}")
    public ResponseEntity<SupplierDto> updateOrder(@PathVariable Long id,
                                                  @RequestBody SupplierDto supplierDto){

        SupplierDto existingProduct = supplierService.getSupplierById(id);
        if(existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        supplierDto.setId(id);
        supplierService.save(supplierDto);
        return ResponseEntity.ok(supplierDto);
    }
    //@PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/admin/suppliers/{id}")
    public ResponseEntity<ApiResponse> deleteOrder(@PathVariable Long id){
        SupplierDto existingOrder = supplierService.getSupplierById(id);
        if(existingOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            supplierService.deleteSupplierById(id);
            Long deletedId = existingOrder.getId();
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse("Id :"+deletedId +",is deleted.",true)
                    ,HttpStatus.OK);
        }
    }
}
