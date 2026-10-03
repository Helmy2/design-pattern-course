package com.course.cleancode.functions;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class FunctionsAndCqsTest {

    @Test
    @DisplayName("Verify ProductCatalogService search products works with polyadic parameters in starter")
    void testSearchProductsPolyadic() {
        ProductCatalogService service = new ProductCatalogService();
        List<ProductCatalogService.Product> results = service.searchProducts(
                "Laptop", 500.0, 2000.0, LocalDate.now().minusDays(30), LocalDate.now(), true, 0, 10
        );

        assertThat(results).isNotEmpty();
        assertThat(results.get(0).name()).isEqualTo("Laptop");
    }

    @Test
    @DisplayName("Verify BillingService flag argument execution")
    void testBillingServiceFlagExecution() {
        BillingService billing = new BillingService();
        BillingService.Invoice invoice = new BillingService.Invoice("INV-001", 199.99, "john@example.com", "+1234567890");

        billing.processInvoice(invoice, true);
        assertThat(billing.getAuditLogs()).contains("CHARGED_IMMEDIATE:199.99", "SMS_SENT:+1234567890");

        billing.processInvoice(invoice, false);
        assertThat(billing.getAuditLogs()).contains("BATCH_QUEUED:199.99", "EMAIL_SENT:john@example.com");
    }

    @Test
    @DisplayName("Verify SessionCacheManager CQS violation behavior")
    void testSessionCacheCqsViolation() {
        SessionCacheManager manager = new SessionCacheManager();
        String key = "sess-123";
        manager.addSession(key, new SessionCacheManager.Session(key, Instant.now().minus(1, ChronoUnit.HOURS)));

        assertThat(manager.contains(key)).isTrue();
        // checkAndEvictIfExpired mutates state while querying
        boolean evicted = manager.checkAndEvictIfExpired(key);
        assertThat(evicted).isTrue();
        assertThat(manager.contains(key)).isFalse();
    }
}
