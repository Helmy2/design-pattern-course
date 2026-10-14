package com.course.cleancode.functions;

import java.time.LocalDate;
import java.util.List;

/**
 * STARTER ANTI-PATTERN: Polyadic Method & Primitive Obsession
 * searchProducts takes 8 positional arguments, causing high cognitive load
 * and fragile call sites where arguments can easily be transposed.
 */
public class ProductCatalogService {

    public record Product(String id, String name, double price, LocalDate dateAdded, boolean inStock) {}

    public List<Product> searchProducts(
            String query,
            Double minPrice,
            Double maxPrice,
            LocalDate startDate,
            LocalDate endDate,
            boolean inStockOnly,
            int pageNumber,
            int pageSize
    ) {
        // Simulated filtering
        return List.of(new Product("P1", query != null ? query : "Standard Item", 99.0, LocalDate.now(), true));
    }
}
