package com.amnil.invbackend.controller.csv;

import com.amnil.invbackend.dto.core.ProductDto;
import com.amnil.invbackend.service.ProductService;
import com.amnil.invbackend.service.csv.CsvExportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class CsvExportController {

    private final CsvExportService csvExportService;
    private final ProductService productService;


    @GetMapping("/products")
    public void exportProductsToCsv() throws IOException {
        List<ProductDto> products = productService.getAllProducts();
        csvExportService.exportProductsToCsv(products, "products.csv");
    }

    // Similar method for exporting orders to CSV
}
