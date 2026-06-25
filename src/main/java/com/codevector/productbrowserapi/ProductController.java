package com.codevector.productbrowserapi;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<Product> getProducts(

            @RequestParam(defaultValue = "20") int size,

            @RequestParam(required = false) String category,

            @RequestParam(required = false) LocalDateTime cursor,

            @RequestParam(required = false) Long lastId

    ) {

        if (category != null && !category.isBlank()) {
            return productService.getProductsByCategory(
                    category,
                    size,
                    cursor,
                    lastId);
        }

        return productService.getProducts(
                size,
                cursor,
                lastId);
    }
}