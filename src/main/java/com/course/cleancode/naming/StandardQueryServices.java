package com.course.cleancode.naming;

/**
 * CLEAN CODE SOLUTION:
 * One Word per Concept:
 * Standardized on 'findById' across all domain querying services.
 */
public class StandardQueryServices {

    public static class UserService {
        public String findById(String id) {
            return "User-" + id;
        }
    }

    public static class OrderService {
        public String findById(String id) {
            return "Order-" + id;
        }
    }

    public static class PaymentService {
        public String findById(String id) {
            return "Payment-" + id;
        }
    }

    public static class InventoryService {
        public String findById(String id) {
            return "Item-" + id;
        }
    }
}
