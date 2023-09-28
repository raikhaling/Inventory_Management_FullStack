package com.amnil.invbackend.service.csv;

import com.amnil.invbackend.dto.ProductDto;


import java.io.IOException;
import java.util.List;

public interface CsvImportService {
    List<ProductDto> importProductsFromCsv(String filePath) throws IOException;
}
