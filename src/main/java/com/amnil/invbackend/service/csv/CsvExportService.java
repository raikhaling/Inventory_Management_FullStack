package com.amnil.invbackend.service.csv;

import com.amnil.invbackend.dto.core.ProductDto;

import java.io.IOException;
import java.util.List;

public interface CsvExportService {
    void exportProductsToCsv(List<ProductDto> products, String filePath) throws IOException;
}
