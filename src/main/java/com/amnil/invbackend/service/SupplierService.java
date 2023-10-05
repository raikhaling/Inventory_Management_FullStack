package com.amnil.invbackend.service;

import com.amnil.invbackend.dto.core.SupplierDto;

import java.util.List;

/**
 * The interface Supplier service.
 */
public interface SupplierService {
    /**
     * Gets supplier by id.
     *
     * @param id the id
     * @return the supplier by id
     */
    SupplierDto getSupplierById(Long id);

    /**
     * Gets suppliers.
     *
     * @return the suppliers
     */
    List<SupplierDto> getSuppliers();

    /**
     * Save supplier dto.
     *
     * @param supplier the supplier
     * @return the supplier dto
     */
    SupplierDto save(SupplierDto supplier);

    /**
     * Delete supplier by id.
     *
     * @param id the id
     */
    void deleteSupplierById(Long id);
}
