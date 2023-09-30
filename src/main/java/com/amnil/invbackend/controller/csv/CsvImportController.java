package com.amnil.invbackend.controller.csv;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.service.csv.CsvImportService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/import")
@RequiredArgsConstructor
public class CsvImportController {

    private final CsvImportService csvImportService;

    @PostMapping("/products")
    public ResponseEntity<List<ProductDto>> importProducts(@RequestParam("file") MultipartFile file) {
        try {
            // Save the uploaded file to a temporary location or process it directly
            String filePath ="/home/retroriff/Documents/"+file.getOriginalFilename();
            file.transferTo(new File(filePath));

            List<ProductDto> importedProducts = csvImportService.importProductsFromCsv(filePath);

            // Process the imported products (e.g., save to the database)


            return ResponseEntity.ok(importedProducts);
        } catch (IOException e) {
            return ResponseEntity.badRequest().build();
        }
    }
}