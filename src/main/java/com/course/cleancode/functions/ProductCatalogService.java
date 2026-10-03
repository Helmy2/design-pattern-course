package com.course.cleancode.functions;

import java.time.LocalDate;
import java.util.List;

/**
 * STARTER CODE:
 * Polyadic method with 8 parameters - fragile and error prone at call site.
 */
public class ProductCatalogService {

    public record Product(String id, String name, double price, LocalDate dateAdded, boolean inStock) {}

    // TODO: Step 1 - Introduce Parameter Object: PriceRange, DateRange, and ProductSearchCriteria
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
