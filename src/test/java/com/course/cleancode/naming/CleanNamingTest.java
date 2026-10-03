package com.course.cleancode.naming;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.Instant;
import java.util.Set;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CleanNamingTest {

    @Test
    @DisplayName("Verify legacy DtaRcrd102 calculation works, but smells are still present in starter")
    void testLegacyRecordCalculations() {
        Instant now = Instant.now();
        DtaRcrd102 record = new DtaRcrd102(now, now, "REC-102");

        assertThat(record.getPszrId()).isEqualTo("REC-102");
        assertThat(record.calculateSessionExpiry(7)).isEqualTo(86400);
        assertThat(record.calculateSessionExpiry(1)).isEqualTo(3600);
        assertThat(record.countActiveUsers(Set.of("alice", "bob"))).isEqualTo(2);
    }

    @Test
    @DisplayName("Verify ProcessOrder starter behavior")
    void testProcessOrderBehavior() {
        ProcessOrder po = new ProcessOrder();
        po.transaction(150.0);
        assertThat(po.flag()).isFalse();

        po.setExpired(true);
        assertThat(po.flag()).isTrue();

        assertThatThrownBy(() -> po.transaction(-10.0))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify Address getter behavior")
    void testAddressPrefixes() {
        Address address = new Address("123 Main St", "Cairo", "11511");
        assertThat(address.getAddrStreet()).isEqualTo("123 Main St");
        assertThat(address.getAddrCity()).isEqualTo("Cairo");
        assertThat(address.getAddrZipCode()).isEqualTo("11511");
    }

    @Test
    @DisplayName("Verify inconsistent query naming across services")
    void testInconsistentQueryServices() {
        LegacyQueryServices.UserService userService = new LegacyQueryServices.UserService();
        LegacyQueryServices.OrderService orderService = new LegacyQueryServices.OrderService();
        LegacyQueryServices.PaymentService paymentService = new LegacyQueryServices.PaymentService();
        LegacyQueryServices.InventoryService inventoryService = new LegacyQueryServices.InventoryService();

        assertThat(userService.fetchUser("1")).isEqualTo("User-1");
        assertThat(orderService.retrieveOrder("2")).isEqualTo("Order-2");
        assertThat(paymentService.getPayment("3")).isEqualTo("Payment-3");
        assertThat(inventoryService.findItem("4")).isEqualTo("Item-4");
    }
}
