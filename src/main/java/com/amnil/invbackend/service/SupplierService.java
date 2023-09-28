package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.SupplierDto;
import com.amnil.invbackend.entity.Supplier;

import java.util.List;

public interface SupplierService {
    SupplierDto getSupplierById(Long id);
    List<SupplierDto> getSuppliers();
    SupplierDto save(SupplierDto supplier);
    void deleteSupplierById(Long id);
}
