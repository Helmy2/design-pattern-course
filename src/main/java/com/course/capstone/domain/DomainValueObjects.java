package com.course.capstone.domain;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DomainValueObjects {

    public record OrderId(String value) {
        public OrderId {
            if (value == null || !value.startsWith("ORD-")) {
                throw new IllegalArgumentException("Invalid order ID: " + value);
            }
        }
    }

    public record Email(String value) {
        public Email {
            if (value == null || !value.contains("@")) {
                throw new IllegalArgumentException("Invalid email format: " + value);
            }
        }
    }

    public record Money(BigDecimal amount) {
        public static final Money ZERO = new Money(BigDecimal.ZERO);

        public Money(double amount) {
            this(BigDecimal.valueOf(amount).setScale(2, RoundingMode.HALF_UP));
        }

        public Money add(Money other) {
            return new Money(this.amount.add(other.amount));
        }

        public Money multiply(double factor) {
            return new Money(this.amount.multiply(BigDecimal.valueOf(factor)).setScale(2, RoundingMode.HALF_UP));
        }

        public long toCents() {
            return this.amount.multiply(BigDecimal.valueOf(100)).longValue();
        }

        public double toDouble() {
            return this.amount.doubleValue();
        }
    }
}
