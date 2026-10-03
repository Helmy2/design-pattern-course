package com.course.cleancode.naming;

/**
 * STARTER CODE:
 * Inconsistent domain terminology: mixing fetch, retrieve, get, and find for identical behavior.
 */
public class LegacyQueryServices {

    public static class UserService {
        // TODO: Step 7 - Standardize query method name to findById
        public String fetchUser(String id) {
            return "User-" + id;
        }
    }

    public static class OrderService {
        // TODO: Step 7 - Standardize query method name to findById
        public String retrieveOrder(String id) {
            return "Order-" + id;
        }
    }

    public static class PaymentService {
        // TODO: Step 7 - Standardize query method name to findById
        public String getPayment(String id) {
            return "Payment-" + id;
        }
    }

    public static class InventoryService {
        // Standard convention: findById
        public String findItem(String id) {
            return "Item-" + id;
        }
    }
}
