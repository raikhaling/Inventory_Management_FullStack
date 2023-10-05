package com.amnil.invbackend.service.csv;

import com.amnil.invbackend.dto.core.ProductDto;

import java.io.IOException;
import java.util.List;

/**
 * The interface Csv export service.
 */
public interface CsvExportService {
    /**
     * Export products to csv.
     *
     * @param products the products
     * @param filePath the file path
     * @throws IOException the io exception
     */
    void exportProductsToCsv(List<ProductDto> products, String filePath) throws IOException;
}
