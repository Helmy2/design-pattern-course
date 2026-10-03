package com.course.cleancode.functions;

import java.time.LocalDate;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * - Cohesive Parameter Objects encapsulate related arguments
 * - Clean method signature with high clarity at call site
 */
public class ProductCatalogService {

    public record PriceRange(Double min, Double max) {
        public PriceRange {
            if (min != null && max != null && min > max) {
                throw new IllegalArgumentException("Minimum price cannot exceed maximum price");
            }
        }
    }

    public record DateRange(LocalDate start, LocalDate end) {
        public DateRange {
            if (start != null && end != null && start.isAfter(end)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }
        }
    }

    public record ProductSearchCriteria(
            String query,
            PriceRange priceRange,
            DateRange dateRange,
            boolean inStockOnly
    ) {}

    public record PageRequest(int pageNumber, int pageSize) {}

    public record Product(String id, String name, double price, LocalDate dateAdded, boolean inStock) {}

    // Dyadic method: clear and cohesive
    public List<Product> searchProducts(ProductSearchCriteria criteria, PageRequest pageRequest) {
        String name = (criteria.query() != null) ? criteria.query() : "Standard Item";
        return List.of(new Product("P1", name, 99.0, LocalDate.now(), true));
    }
}
