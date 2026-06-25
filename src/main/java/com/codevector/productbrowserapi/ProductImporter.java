package com.codevector.productbrowserapi;

import org.springframework.boot.CommandLineRunner;
import org.springframework.web.client.RestTemplate;

//@Component
public class ProductImporter implements CommandLineRunner {

    private final ProductRepository repository;

    public ProductImporter(ProductRepository repository) {
        this.repository = repository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (repository.count() > 0) {
            return;
        }

        RestTemplate restTemplate = new RestTemplate();

        Product[] products = restTemplate.getForObject(
                "https://fakestoreapi.com/products",
                Product[].class);

        if (products != null) {
            repository.saveAll(java.util.Arrays.asList(products));
            System.out.println(
                    "Products Imported: " + products.length);
        }
    }
}