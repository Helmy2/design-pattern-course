package com.course.cleancode.naming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CleanNamingTest {

    @Test
    @DisplayName("Verify CustomerRecord with pronounceable names and constants")
    void testCustomerRecordCleanCalculations() {
        Instant now = Instant.now();
        CustomerRecord record = new CustomerRecord(now, now, "REC-102");

        assertThat(record.getRecordId()).isEqualTo("REC-102");
        assertThat(record.getGenerationTimestamp()).isEqualTo(now);
        assertThat(record.getModificationTimestamp()).isEqualTo(now);

        assertThat(record.calculateSessionExpiry(CustomerRecord.ROLE_ADMIN))
                .isEqualTo(CustomerRecord.SECONDS_IN_A_DAY);
        assertThat(record.calculateSessionExpiry(1))
                .isEqualTo(CustomerRecord.DEFAULT_SESSION_EXPIRY_SECONDS);

        assertThat(record.countActiveUsers(Set.of("alice", "bob"))).isEqualTo(2);
    }

    @Test
    @DisplayName("Verify OrderProcessor with verb methods and predicate naming")
    void testOrderProcessorCleanBehavior() {
        OrderProcessor processor = new OrderProcessor();
        processor.processPayment(250.0);
        assertThat(processor.hasExpired()).isFalse();

        processor.setExpired(true);
        assertThat(processor.hasExpired()).isTrue();

        assertThatThrownBy(() -> processor.processPayment(-10.0))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("greater than zero");
    }

    @Test
    @DisplayName("Verify Address without redundant addr prefixes")
    void testAddressCleanNaming() {
        Address address = new Address("45 Nile Corniche", "Cairo", "11511");
        assertThat(address.getStreet()).isEqualTo("45 Nile Corniche");
        assertThat(address.getCity()).isEqualTo("Cairo");
        assertThat(address.getZipCode()).isEqualTo("11511");
    }

    @Test
    @DisplayName("Verify StandardQueryServices consistently using findById across services")
    void testStandardizedQueryServices() {
        StandardQueryServices.UserService userService = new StandardQueryServices.UserService();
        StandardQueryServices.OrderService orderService = new StandardQueryServices.OrderService();
        StandardQueryServices.PaymentService paymentService = new StandardQueryServices.PaymentService();
        StandardQueryServices.InventoryService inventoryService = new StandardQueryServices.InventoryService();

        assertThat(userService.findById("101")).isEqualTo("User-101");
        assertThat(orderService.findById("202")).isEqualTo("Order-202");
        assertThat(paymentService.findById("303")).isEqualTo("Payment-303");
        assertThat(inventoryService.findById("404")).isEqualTo("Item-404");
    }
}
