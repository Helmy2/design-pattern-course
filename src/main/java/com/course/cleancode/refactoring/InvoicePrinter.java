package com.course.cleancode.refactoring;

/**
 * STARTER CODE:
 * Monolithic method mixing presentation, nested arithmetic, and output.
 */
public class InvoicePrinter {

    public record Invoice(String customerName, int quantity, double itemPrice) {}

    // TODO: Step 1 - Extract Method and Extract Variable:
    // Extract calculateTotal(Invoice), printBanner(), printDetails(name, total)
    public String printInvoice(Invoice invoice) {
        StringBuilder output = new StringBuilder();

        // Banner
        output.append("***********************\n");
        output.append("**** Customer Owes ****\n");
        output.append("***********************\n");

        // Complex formula without descriptive names
        double basePrice = invoice.quantity() * invoice.itemPrice();
        double discount = Math.max(0, invoice.quantity() - 50) * invoice.itemPrice() * 0.05;
        double shipping = Math.min(basePrice * 0.1, 100.0);
        double total = basePrice - discount + shipping;

        // Details
        output.append("name: ").append(invoice.customerName()).append("\n");
        output.append("amount: ").append(total);

        return output.toString();
    }
}
