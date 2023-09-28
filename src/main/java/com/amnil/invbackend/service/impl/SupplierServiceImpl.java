package com.amnil.invbackend.service.impl;

import com.amnil.invbackend.dto.ProductDto;
import com.amnil.invbackend.dto.SupplierDto;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.entity.Supplier;
import com.amnil.invbackend.exception.EntityNotFoundException;
import com.amnil.invbackend.repository.SupplierRepository;
import com.amnil.invbackend.service.SupplierService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SupplierServiceImpl implements SupplierService {
    private final SupplierRepository supplierRepository;
    private final ModelMapper modelMapper;
    @Override
    public SupplierDto getSupplierById(Long id) {
        Supplier supplier= supplierRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Supplier not found with id: " + id));
        return modelMapper.map(supplier, SupplierDto.class);
    }

    @Override
    public List<SupplierDto> getSuppliers() {
        List<Supplier> suppliers = supplierRepository.findAll();
        List<SupplierDto> supplierDtos = suppliers.stream()
                .map(supplier -> modelMapper.map(supplier,SupplierDto.class))
                .collect(Collectors.toList());
        return supplierDtos;
    }

    @Override
    public SupplierDto save(SupplierDto supplierDto) {
        Supplier supplier = modelMapper.map(supplierDto,Supplier.class);
        supplierRepository.save(supplier);
        return modelMapper.map(supplier, SupplierDto.class);
    }

    @Override
    public void deleteSupplierById(Long id) {
      Supplier supplier = supplierRepository.findById(id).
              orElseThrow(()->new EntityNotFoundException("User does not exist with id: "+id));
        supplierRepository.deleteById(id);

    }
}
