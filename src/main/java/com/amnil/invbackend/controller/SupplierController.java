package com.amnil.invbackend.controller;

import com.amnil.invbackend.dto.ApiResponse;
import com.amnil.invbackend.dto.core.SupplierDto;
import com.amnil.invbackend.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * The type Supplier controller.
 */
@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor
public class SupplierController {
    /**
     * supplierService
     */
    private final SupplierService supplierService;

    /**
     * Get supplier by id response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @GetMapping("/public/suppliers/{id}")
    public ResponseEntity<SupplierDto> getSupplierById(@PathVariable Long id){
        SupplierDto supplierDto = supplierService.getSupplierById(id);
        if(supplierDto != null)
            return ResponseEntity.ok(supplierDto);
        else
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    @GetMapping("/public/suppliers")
    public ResponseEntity<List<SupplierDto>> getSuppliers(){
        List<SupplierDto> suppliers = supplierService.getSuppliers();
        if(!suppliers.isEmpty())
            return ResponseEntity.ok(suppliers);
        else
            //return ResponseEntity.notFound().build();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

    }

    /**
     * Create supplier response entity.
     *
     * @param supplierDto the supplier dto
     * @return the response entity
     */
    @PostMapping("/admin/suppliers")
    public ResponseEntity<SupplierDto> createSupplier( @RequestBody SupplierDto supplierDto){
        SupplierDto supplierDto1 = supplierService.save(supplierDto);
        return ResponseEntity.ok(supplierDto1);
    }

    /**
     * Update supplier response entity.
     *
     * @param id          the id
     * @param supplierDto the supplier dto
     * @return the response entity
     */
    @PutMapping("/admin/suppliers/{id}")
    public ResponseEntity<SupplierDto> updateSupplier(@PathVariable Long id,
                                                  @RequestBody SupplierDto supplierDto){

        SupplierDto existingProduct = supplierService.getSupplierById(id);
        if(existingProduct == null) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        supplierDto.setSupplierId(id);
        supplierService.save(supplierDto);
        return ResponseEntity.ok(supplierDto);
    }

    /**
     * Delete supplier response entity.
     *
     * @param id the id
     * @return the response entity
     */
    @DeleteMapping("/admin/suppliers/{id}")
    public ResponseEntity<ApiResponse> deleteSupplier(@PathVariable Long id){
        SupplierDto existingOrder = supplierService.getSupplierById(id);
        if(existingOrder == null){
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        else {
            supplierService.deleteSupplierById(id);
            Long deletedId = existingOrder.getSupplierId();
            return new ResponseEntity<ApiResponse>(
                    new ApiResponse("Id :"+deletedId +",is deleted.",true)
                    ,HttpStatus.OK);
        }
    }
}
