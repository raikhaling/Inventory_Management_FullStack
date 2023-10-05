package com.amnil.invbackend.service.csv;

import com.amnil.invbackend.dto.core.ProductDto;


import java.io.IOException;
import java.util.List;

/**
 * The interface Csv import service.
 */
public interface CsvImportService {
    /**
     * Import products from csv list.
     *
     * @param filePath the file path
     * @return the list
     * @throws IOException the io exception
     */
    List<ProductDto> importProductsFromCsv(String filePath) throws IOException;
}
