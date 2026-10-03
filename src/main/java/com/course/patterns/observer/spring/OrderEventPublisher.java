package com.course.patterns.observer.spring;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * MODERN ENTERPRISE REALITY:
 * Spring ApplicationEventPublisher / Event Bus:
 * Decouples domain services from multiple downstream event listeners.
 */
public class OrderEventPublisher {

    public record OrderPlacedEvent(Long orderId, String customerEmail, BigDecimal totalAmount) {}

    @FunctionalInterface
    public interface OrderEventListener {
        void onOrderPlaced(OrderPlacedEvent event);
    }

    public static class EventBus {
        private final List<OrderEventListener> listeners = new ArrayList<>();

        public void register(OrderEventListener listener) {
            listeners.add(listener);
        }

        public void publish(OrderPlacedEvent event) {
            for (OrderEventListener listener : listeners) {
                listener.onOrderPlaced(event);
            }
        }
    }
}
