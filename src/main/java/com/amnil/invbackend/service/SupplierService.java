package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.SupplierDto;

import java.util.List;

public interface SupplierService {
    SupplierDto getSupplierById(Long id);
    List<SupplierDto> getSuppliers();
    SupplierDto save(SupplierDto supplier);
    void deleteSupplierById(Long id);
}
