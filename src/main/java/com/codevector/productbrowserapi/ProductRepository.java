package com.codevector.productbrowserapi;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;
import java.util.List;

public interface ProductRepository extends JpaRepository<Product, Long> {

    @Query("""
            SELECT p
            FROM Product p
            ORDER BY p.updatedAt DESC, p.id DESC
            """)
    List<Product> findFirstPage(Pageable pageable);

    @Query("""
            SELECT p
            FROM Product p
            WHERE
            p.updatedAt < :cursor
            OR
            (p.updatedAt = :cursor AND p.id < :lastId)
            ORDER BY p.updatedAt DESC, p.id DESC
            """)
    List<Product> findNextPage(
            LocalDateTime cursor,
            Long lastId,
            Pageable pageable);

    @Query("""
            SELECT p
            FROM Product p
            WHERE LOWER(p.category)=LOWER(:category)
            ORDER BY p.updatedAt DESC,p.id DESC
            """)
    List<Product> findFirstPageByCategory(
            String category,
            Pageable pageable);

    @Query("""
            SELECT p
            FROM Product p
            WHERE LOWER(p.category)=LOWER(:category)
            AND
            (
            p.updatedAt < :cursor
            OR
            (p.updatedAt=:cursor AND p.id<:lastId)
            )
            ORDER BY p.updatedAt DESC,p.id DESC
            """)
    List<Product> findNextPageByCategory(
            String category,
            LocalDateTime cursor,
            Long lastId,
            Pageable pageable);
}