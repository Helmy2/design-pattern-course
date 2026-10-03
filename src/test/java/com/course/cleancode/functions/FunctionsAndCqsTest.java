package com.course.cleancode.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class FunctionsAndCqsTest {

    @Test
    @DisplayName("Verify ProductCatalogService with Parameter Objects")
    void testSearchProductsWithParameterObjects() {
        ProductCatalogService service = new ProductCatalogService();
        ProductCatalogService.PriceRange priceRange = new ProductCatalogService.PriceRange(100.0, 500.0);
        ProductCatalogService.DateRange dateRange = new ProductCatalogService.DateRange(
                LocalDate.now().minusDays(7), LocalDate.now()
        );

        ProductCatalogService.ProductSearchCriteria criteria = new ProductCatalogService.ProductSearchCriteria(
                "Monitor", priceRange, dateRange, true
        );
        ProductCatalogService.PageRequest pageRequest = new ProductCatalogService.PageRequest(0, 10);

        List<ProductCatalogService.Product> results = service.searchProducts(criteria, pageRequest);
        assertThat(results).isNotEmpty();
        assertThat(results.get(0).name()).isEqualTo("Monitor");

        // Invariant validation
        assertThatThrownBy(() -> new ProductCatalogService.PriceRange(600.0, 100.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify BillingService explicit methods replace flag argument")
    void testBillingServiceExplicitMethods() {
        BillingService billing = new BillingService();
        BillingService.Invoice invoice = new BillingService.Invoice("INV-99", 500.0, "vip@example.com", "+201000000000");

        billing.processUrgentInvoice(invoice);
        assertThat(billing.getAuditLogs()).contains("CHARGED_IMMEDIATE:500.0", "SMS_SENT:+201000000000");

        billing.processStandardInvoice(invoice);
        assertThat(billing.getAuditLogs()).contains("BATCH_QUEUED:500.0", "EMAIL_SENT:vip@example.com");
    }

    @Test
    @DisplayName("Verify SessionCacheManager strictly adheres to CQS")
    void testSessionCacheCqsCompliance() {
        SessionCacheManager manager = new SessionCacheManager();
        String key = "sess-abc";
        manager.addSession(key, new SessionCacheManager.Session(key, Instant.now().minus(5, ChronoUnit.MINUTES)));

        // Query check: does NOT mutate state
        boolean expired = manager.isExpired(key);
        assertThat(expired).isTrue();
        assertThat(manager.contains(key)).isTrue(); // Still present after query!

        // Command execution: mutates state
        manager.evictSession(key);
        assertThat(manager.contains(key)).isFalse();
    }
}
