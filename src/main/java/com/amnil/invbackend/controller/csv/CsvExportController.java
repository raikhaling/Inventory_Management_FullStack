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

/**
 * The type Csv export controller.
 */
@RestController
@RequestMapping("/api/export")
@RequiredArgsConstructor
public class CsvExportController {

    /**
     * csvExportService
     */
    private final CsvExportService csvExportService;
    /**
     * productService
     */
    private final ProductService productService;


    /**
     * Export products to csv.
     *
     * @throws IOException the io exception
     */
    @GetMapping("/products")
    public void exportProductsToCsv() throws IOException {
        List<ProductDto> products = productService.getAllProducts();
        csvExportService.exportProductsToCsv(products, "products.csv");
    }

    // Similar method for exporting orders to CSV
}
