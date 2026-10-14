package com.course.cleancode.naming;

/**
 * STARTER ANTI-PATTERN: Inconsistent Domain Terminology
 * Arbitrarily mixing fetch, retrieve, get, and find across services for the exact same ID lookup.
 */
public class LegacyQueryServices {

    public static class UserService {
        public String fetchUser(String id) {
            return "User-" + id;
        }
    }

    public static class OrderService {
        public String retrieveOrder(String id) {
            return "Order-" + id;
        }
    }

    public static class PaymentService {
        public String getPayment(String id) {
            return "Payment-" + id;
        }
    }

    public static class InventoryService {
        public String findItem(String id) {
            return "Item-" + id;
        }
    }
}
