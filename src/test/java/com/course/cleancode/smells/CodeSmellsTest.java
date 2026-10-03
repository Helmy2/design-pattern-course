package com.course.cleancode.smells;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class CodeSmellsTest {

    @Test
    @DisplayName("Verify Domain Value Objects Email and PhoneNumber self-validation")
    void testValueObjectsSelfValidation() {
        UserRegistrationService service = new UserRegistrationService();
        UserRegistrationService.Email email = new UserRegistrationService.Email("engineer@clean.com");
        UserRegistrationService.PhoneNumber phone = new UserRegistrationService.PhoneNumber("+12025550143");

        assertThat(service.registerUser(email, phone)).isTrue();

        assertThatThrownBy(() -> new UserRegistrationService.Email("bad-email"))
                .isInstanceOf(IllegalArgumentException.class);

        assertThatThrownBy(() -> new UserRegistrationService.PhoneNumber("abc"))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify Polymorphic DiscountPolicy Strategy replaces switch ladder")
    void testDiscountPolicyPolymorphism() {
        DiscountCalculator calculator = new DiscountCalculator();

        assertThat(calculator.calculate(new DiscountCalculator.RegularDiscount(), 100.0)).isEqualTo(100.0);
        assertThat(calculator.calculate(new DiscountCalculator.PremiumDiscount(), 100.0)).isEqualTo(90.0);
        assertThat(calculator.calculate(new DiscountCalculator.VipDiscount(), 100.0)).isEqualTo(80.0);
    }

    @Test
    @DisplayName("Verify Segregated Bird Interfaces satisfy Liskov Substitution Principle")
    void testBirdInterfaceSegregation() {
        BirdHierarchy.FlyingBird eagle = new BirdHierarchy.Eagle();
        assertThat(eagle.eat()).isEqualTo("Eagle eating");
        assertThat(eagle.fly()).isEqualTo("Eagle soaring high");

        BirdHierarchy.Bird penguin = new BirdHierarchy.Penguin();
        assertThat(penguin.eat()).isEqualTo("Penguin eating fish");
        // Penguin is not a FlyingBird, no UnsupportedOperationException risk!
    }

    @Test
    @DisplayName("Verify Tell Don't Ask and Law of Demeter in Order processing")
    void testTellDontAskAndDemeter() {
        OrderReceiptPrinter.Country usa = new OrderReceiptPrinter.Country("US", "United States");
        OrderReceiptPrinter.Address address = new OrderReceiptPrinter.Address("Wall St", usa);
        OrderReceiptPrinter.Customer customer = new OrderReceiptPrinter.Customer("Corp", 0.10, address);
        OrderReceiptPrinter.Order order = new OrderReceiptPrinter.Order(
                List.of(new OrderReceiptPrinter.OrderItem("Book", 50.0, 2)),
                customer
        );

        // Tell, Don't Ask: calculation is on Order
        assertThat(order.calculateTotalWithTax()).isEqualTo(110.0);

        // Law of Demeter: delivery country code without chaining getters
        assertThat(order.getDeliveryCountryCode()).isEqualTo("US");

        OrderReceiptPrinter printer = new OrderReceiptPrinter();
        assertThat(printer.printReceipt(order))
                .isEqualTo("Receipt: Total=110.0, Country=US");
    }
}
