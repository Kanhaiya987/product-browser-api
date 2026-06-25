package com.codevector.productbrowserapi.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

// @Component
public class DataSeeder implements CommandLineRunner {

    private final JdbcTemplate jdbcTemplate;

    public DataSeeder(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void run(String... args) {

        Integer count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM products",
                Integer.class
        );

        System.out.println("Current Count = " + count);

        if (count != null && count > 0) {
            System.out.println("Products already exist. Skipping...");
            return;
        }

        final int TOTAL_PRODUCTS = 200000;
        final int BATCH_SIZE = 1000;

        Random random = new Random();

        String[] categories = {
                "electronics",
                "fashion",
                "books",
                "sports",
                "home"
        };

        String sql = """
                INSERT INTO products
                (title, price, category, description, image, created_at, updated_at)
                VALUES (?, ?, ?, ?, ?, ?, ?)
                """;

        List<Object[]> batchArgs = new ArrayList<>();

        long start = System.currentTimeMillis();

        for (int i = 1; i <= TOTAL_PRODUCTS; i++) {

            LocalDateTime time =
                    LocalDateTime.now().minusDays(random.nextInt(365));

            batchArgs.add(new Object[]{
                    "Product " + i,
                    100 + random.nextDouble() * 50000,
                    categories[random.nextInt(categories.length)],
                    "Description for product " + i,
                    "image" + i + ".jpg",
                    Timestamp.valueOf(time),
                    Timestamp.valueOf(time)
            });

            if (batchArgs.size() == BATCH_SIZE) {

                jdbcTemplate.batchUpdate(sql, batchArgs);

                System.out.println("Inserted : " + i);

                batchArgs.clear();
            }
        }

        if (!batchArgs.isEmpty()) {
            jdbcTemplate.batchUpdate(sql, batchArgs);
        }

        long end = System.currentTimeMillis();

        System.out.println("------------------------------------");
        System.out.println("Inserted " + TOTAL_PRODUCTS + " products");
        System.out.println("Time : " + (end - start) / 1000 + " sec");
        System.out.println("------------------------------------");
    }
}