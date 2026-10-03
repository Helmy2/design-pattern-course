package com.course.cleancode.smells;

import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * 1. Tell, Don't Ask: Calculation moved directly into Order class.
 * 2. Law of Demeter: Intermediary delegation avoids message chains.
 */
public class OrderReceiptPrinter {

    public record Country(String code, String name) {}
    public record Address(String street, Country country) {
        public String getCountryCode() { return country.code(); }
    }
    public record Customer(String name, double taxRate, Address address) {
        public String getDeliveryCountryCode() { return address.getCountryCode(); }
    }
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

        // Tell, Don't Ask: Logic belongs to the data owner
        public double calculateTotalWithTax() {
            double subtotal = items.stream()
                    .mapToDouble(OrderItem::getSubtotal)
                    .sum();
            return subtotal + (subtotal * customer.taxRate());
        }

        // Law of Demeter: delegate through customer
        public String getDeliveryCountryCode() {
            return customer.getDeliveryCountryCode();
        }
    }

    public String printReceipt(Order order) {
        double total = order.calculateTotalWithTax();
        String country = order.getDeliveryCountryCode();
        return "Receipt: Total=" + total + ", Country=" + country;
    }
}
