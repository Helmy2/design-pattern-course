package com.course.patterns.observer.spring;

import java.util.ArrayList;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * Modern Enterprise Spring Event-Driven Architecture:
 * Replaces in-memory GoF observer with decoupled ApplicationEventPublisher.
 */
public class OrderEventPublisher {

    public record OrderPlacedEvent(String orderId, String customerEmail, double amount) {}

    public interface OrderEventListener {
        void onApplicationEvent(OrderPlacedEvent event);
    }

    public static class AuditLogListener implements OrderEventListener {
        private final List<String> logs = new ArrayList<>();

        @Override
        public void onApplicationEvent(OrderPlacedEvent event) {
            logs.add("AUDIT: Order " + event.orderId() + " placed for $" + event.amount());
        }

        public List<String> getLogs() {
            return logs;
        }
    }

    public static class EmailNotificationListener implements OrderEventListener {
        private final List<String> sentEmails = new ArrayList<>();

        @Override
        public void onApplicationEvent(OrderPlacedEvent event) {
            sentEmails.add("EMAIL to " + event.customerEmail() + ": Confirmation for " + event.orderId());
        }

        public List<String> getSentEmails() {
            return sentEmails;
        }
    }

    public static class DomainEventPublisher {
        private final List<OrderEventListener> listeners = new ArrayList<>();

        public void registerListener(OrderEventListener listener) {
            listeners.add(listener);
        }

        public void publishEvent(OrderPlacedEvent event) {
            for (OrderEventListener listener : listeners) {
                listener.onApplicationEvent(event);
            }
        }
    }
}
