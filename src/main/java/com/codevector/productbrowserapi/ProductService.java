package com.codevector.productbrowserapi;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

    private final ProductRepository repository;

    public ProductService(ProductRepository repository) {
        this.repository = repository;
    }

    public List<Product> getProducts(
            int size,
            LocalDateTime cursor,
            Long lastId) {

        if (cursor == null || lastId == null) {
            return repository.findFirstPage(PageRequest.of(0, size));
        }

        return repository.findNextPage(
                cursor,
                lastId,
                PageRequest.of(0, size));
    }

    public List<Product> getProductsByCategory(
            String category,
            int size,
            LocalDateTime cursor,
            Long lastId) {

        if (cursor == null || lastId == null) {
            return repository.findFirstPageByCategory(
                    category,
                    PageRequest.of(0, size));
        }

        return repository.findNextPageByCategory(
                category,
                cursor,
                lastId,
                PageRequest.of(0, size));
    }
}