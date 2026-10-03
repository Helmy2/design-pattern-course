package com.course.capstone.domain;

import com.course.capstone.domain.DomainValueObjects.Email;
import com.course.capstone.domain.DomainValueObjects.Money;
import com.course.capstone.domain.DomainValueObjects.OrderId;

/**
 * Domain Entity: Encapsulates order state and calculations (Tell, Don't Ask).
 */
public class Order {

    private final OrderId id;
    private final Email customerEmail;
    private final String countryCode;
    private final Money itemPrice;
    private final int quantity;
    private final boolean expressDelivery;
    private final boolean discountEligible;

    public Order(OrderId id, Email customerEmail, String countryCode, Money itemPrice, int quantity, boolean expressDelivery, boolean discountEligible) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity must be positive");
        }
        this.id = id;
        this.customerEmail = customerEmail;
        this.countryCode = countryCode;
        this.itemPrice = itemPrice;
        this.quantity = quantity;
        this.expressDelivery = expressDelivery;
        this.discountEligible = discountEligible;
    }

    public OrderId getId() { return id; }
    public Email getCustomerEmail() { return customerEmail; }
    public String getCountryCode() { return countryCode; }

    public Money calculateSubtotal() {
        Money subtotal = itemPrice.multiply(quantity);
        if (discountEligible && quantity > 10) {
            subtotal = subtotal.multiply(0.90);
        }
        return subtotal;
    }

    public Money calculateTotal(double taxRate) {
        Money subtotal = calculateSubtotal();
        Money tax = subtotal.multiply(taxRate);
        Money total = subtotal.add(tax);
        if (expressDelivery) {
            total = total.add(new Money(25.0));
        }
        return total;
    }
}
