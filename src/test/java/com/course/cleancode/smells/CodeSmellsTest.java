package com.course.cleancode.smells;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CodeSmellsTest {

    @Test
    @DisplayName("Verify UserRegistrationService raw validation in starter")
    void testUserRegistration() {
        UserRegistrationService service = new UserRegistrationService();
        assertThat(service.registerUser("dev@example.com", "+12025550143")).isTrue();

        assertThatThrownBy(() -> service.registerUser("invalid-email", "+12025550143"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify DiscountCalculator switch ladder in starter")
    void testDiscountCalculatorSwitch() {
        DiscountCalculator calculator = new DiscountCalculator();
        assertThat(calculator.applyDiscount(DiscountCalculator.CustomerType.REGULAR, 100.0)).isEqualTo(100.0);
        assertThat(calculator.applyDiscount(DiscountCalculator.CustomerType.PREMIUM, 100.0)).isEqualTo(90.0);
        assertThat(calculator.applyDiscount(DiscountCalculator.CustomerType.VIP, 100.0)).isEqualTo(80.0);
    }

    @Test
    @DisplayName("Verify Refused Bequest throws unexpected exception on Penguin fly")
    void testBirdRefusedBequest() {
        BirdHierarchy.Bird eagle = new BirdHierarchy.Eagle();
        assertThat(eagle.fly()).isEqualTo("Eagle soaring high");

        BirdHierarchy.Bird penguin = new BirdHierarchy.Penguin();
        assertThat(penguin.eat()).isEqualTo("Penguin eating fish");

        // LSP violation verified: client expecting Bird.fly() gets runtime exception
        assertThatThrownBy(penguin::fly)
                .isInstanceOf(UnsupportedOperationException.class)
                .hasMessageContaining("Penguins cannot fly");
    }

    @Test
    @DisplayName("Verify Feature Envy and Demeter message chain in starter")
    void testFeatureEnvyAndDemeter() {
        OrderReceiptPrinter.Country usa = new OrderReceiptPrinter.Country("US", "United States");
        OrderReceiptPrinter.Address address = new OrderReceiptPrinter.Address("Wall St", usa);
        OrderReceiptPrinter.Customer customer = new OrderReceiptPrinter.Customer("Corp", 0.10, address);
        OrderReceiptPrinter.Order order = new OrderReceiptPrinter.Order(
                List.of(new OrderReceiptPrinter.OrderItem("Book", 50.0, 2)),
                customer
        );

        OrderReceiptPrinter printer = new OrderReceiptPrinter();
        // 50 * 2 = 100 + 10% tax = 110
        assertThat(printer.calculateTotalWithTax(order)).isEqualTo(110.0);
        assertThat(printer.getCustomerCountryCode(order)).isEqualTo("US");
    }
}
