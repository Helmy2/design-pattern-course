package com.course.architecture.packagebyfeature.order;

/**
 * CLEAN CODE SOLUTION:
 * Encapsulated within the 'order' feature package.
 * Depends only on its local repository and an abstract UserStatusChecker port.
 */
public class OrderService {
    private final OrderRepository orderRepository;
    private final UserStatusChecker userStatusChecker;

    public OrderService(OrderRepository orderRepository, UserStatusChecker userStatusChecker) {
        this.orderRepository = orderRepository;
        this.userStatusChecker = userStatusChecker;
    }

    public boolean placeOrder(String userId, String orderId) {
        if (userStatusChecker != null && userStatusChecker.isUserActive(userId)) {
            return orderRepository.findOrder(orderId).isPresent();
        }
        return false;
    }

    public void cancelOrdersForUser(String userId) {
        // cancellation logic
    }
}
