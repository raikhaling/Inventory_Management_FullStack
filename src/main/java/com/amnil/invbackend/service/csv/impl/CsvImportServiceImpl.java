package com.amnil.invbackend.service.csv.impl;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.service.csv.CsvImportService;
import com.opencsv.exceptions.CsvValidationException;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import com.opencsv.CSVReader;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CsvImportServiceImpl implements CsvImportService {

    private final ModelMapper modelMapper;


    public CsvImportServiceImpl(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
    }

    @Override
    public List<ProductDto> importProductsFromCsv(String filePath) throws IOException {

        List<Product> products = new ArrayList<>();

        try (CSVReader reader = new CSVReader(new FileReader(filePath))) {
            // Skip the first row (headers)
            reader.skip(1);
            String[] nextLine;
            while ((nextLine = reader.readNext()) != null) {
                Product product = new Product();
                log.info("parsing of import csv started.");
                product.setId(Long.valueOf(nextLine[0]));
                product.setProductName(nextLine[1]);
                log.info("string; :{}",product.getProductName());
                product.setProductPrice(Double.parseDouble(nextLine[2]));
                product.setStock(Boolean.parseBoolean(nextLine[3]));
                product.setProductQuantity(Integer.parseInt(nextLine[4]));
                product.setProductDescription(nextLine[5]);
                log.info("parsing of one import csv finished.");
                products.add(product);
            }
        } catch (CsvValidationException e) {
            throw new RuntimeException(e.getMessage());
        }

        return products.stream().map((element) -> modelMapper.map(element, ProductDto.class)).toList();
    }
}
