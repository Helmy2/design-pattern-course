package com.course.cleancode.smells;

import java.util.List;

/**
 * STARTER CODE:
 * Coupler Smells:
 * 1. Feature Envy: printer knows too much about Order calculations.
 * 2. Message Chain: Law of Demeter violation (order.getCustomer().getAddress().getCountry().getCode()).
 */
public class OrderReceiptPrinter {

    public record Country(String code, String name) {}
    public record Address(String street, Country country) {}
    public record Customer(String name, double taxRate, Address address) {}
    public record OrderItem(String product, double price, int quantity) {
        public double getSubtotal() { return price * quantity; }
    }

    public static class Order {
        private final List<OrderItem> items;
        private final Customer customer;

        public Order(List<OrderItem> items, Customer customer) {
            this.items = items;
            this.customer = customer;
        }

        public List<OrderItem> getItems() { return items; }
        public Customer getCustomer() { return customer; }
    }

    public double calculateTotalWithTax(Order order) {
        double subtotal = 0.0;
        for (OrderItem item : order.getItems()) {
            subtotal += item.price() * item.quantity();
        }
        return subtotal + (subtotal * order.getCustomer().taxRate());
    }

    public String getCustomerCountryCode(Order order) {
        return order.getCustomer().address().country().code();
    }
}
