package com.course.capstone.infrastructure;

import com.course.capstone.domain.DomainValueObjects.Money;
import com.course.capstone.domain.Order;
import com.course.capstone.domain.ports.Ports.NotificationPort;
import com.course.capstone.domain.ports.Ports.OrderRepositoryPort;
import com.course.capstone.domain.ports.Ports.PaymentGatewayPort;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InfrastructureAdapters {

    public static class StripePaymentAdapter implements PaymentGatewayPort {
        private final List<String> audit = new ArrayList<>();

        @Override public Provider getProvider() { return Provider.STRIPE; }

        @Override
        public boolean charge(String customerId, Money amount) {
            audit.add("STRIPE_API_CHARGE:" + amount.toCents() + "_CENTS");
            return true;
        }

        public List<String> getAudit() { return List.copyOf(audit); }
    }

    public static class PayPalPaymentAdapter implements PaymentGatewayPort {
        private final List<String> audit = new ArrayList<>();

        @Override public Provider getProvider() { return Provider.PAYPAL; }

        @Override
        public boolean charge(String customerId, Money amount) {
            audit.add("PAYPAL_API_CAPTURE:" + amount.toDouble() + "_USD");
            return true;
        }

        public List<String> getAudit() { return List.copyOf(audit); }
    }

    public static class EmailNotificationAdapter implements NotificationPort {
        private final List<String> sentEmails = new ArrayList<>();

        @Override
        public void sendOrderConfirmation(String email, Money total) {
            sentEmails.add("SMTP_SEND:" + email + ":TOTAL=" + String.format("%.2f", total.toDouble()));
        }

        public List<String> getSentEmails() { return List.copyOf(sentEmails); }
    }

    public static class InMemoryOrderRepository implements OrderRepositoryPort {
        private final Map<String, Order> store = new HashMap<>();

        @Override
        public void save(Order order) {
            store.put(order.getId().value(), order);
        }

        public boolean contains(String orderId) {
            return store.containsKey(orderId);
        }
    }
}
