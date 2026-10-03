package com.course.cleancode.refactoring;

/**
 * CLEAN CODE SOLUTION:
 * Applied:
 * 1. Extract Method: printBanner(), calculateTotal(), printDetails()
 * 2. Extract Variable: basePrice, quantityDiscount, shippingFee
 */
public class InvoicePrinter {

    public record Invoice(String customerName, int quantity, double itemPrice) {}

    public String printInvoice(Invoice invoice) {
        StringBuilder output = new StringBuilder();
        printBanner(output);
        double total = calculateTotal(invoice);
        printDetails(output, invoice.customerName(), total);
        return output.toString();
    }

    private void printBanner(StringBuilder output) {
        output.append("***********************\n");
        output.append("**** Customer Owes ****\n");
        output.append("***********************\n");
    }

    private double calculateTotal(Invoice invoice) {
        // Extract Variable gives clear semantic names to sub-calculations
        double basePrice = invoice.quantity() * invoice.itemPrice();
        double quantityDiscount = Math.max(0, invoice.quantity() - 50) * invoice.itemPrice() * 0.05;
        double shippingFee = Math.min(basePrice * 0.1, 100.0);

        return basePrice - quantityDiscount + shippingFee;
    }

    private void printDetails(StringBuilder output, String customerName, double total) {
        output.append("name: ").append(customerName).append("\n");
        output.append("amount: ").append(total);
    }
}
