package com.amnil.invbackend.service.csv.impl;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.entity.Product;
import com.amnil.invbackend.service.csv.CsvExportService;
import com.opencsv.CSVWriter;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CsvExportServiceImpl implements CsvExportService {
    private final ModelMapper modelMapper;
    @Override
    public void exportProductsToCsv(List<ProductDto> productsDtos, String filePath)
            throws IOException {
        List<Product> products = productsDtos.stream()
                .map(productDto -> modelMapper.map(productDto,Product.class))
                .toList();
        try (CSVWriter writer = new CSVWriter(new FileWriter(filePath))) {

            String[] header = {"Product ID", "Product Name","Product Price",
                    "Stock","Product Qty", "Product Description"};
            // writes the header row to the CSV file.
            writer.writeNext(header);


            for (Product product : products) {
                String[] data = {
                        //one row of the CSV file.
                        String.valueOf(product.getId()),
                        product.getProductName(),
                        String.valueOf(product.getProductPrice()),
                        String.valueOf(product.getStock()),
                        String.valueOf(product.getProductQuantity()),
                        product.getProductDescription()
                };
                //writes the data row to the CSV file.
                writer.writeNext(data);
            }
        }
    }
}
