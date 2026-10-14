package com.course.architecture.packagebyfeature.order;

/**
 * CLEAN CODE SOLUTION:
 * Controller within the 'order' feature package.
 * Interacts only with OrderService and never accesses OrderRepository directly.
 */
public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public boolean handleOrder(String userId, String orderId) {
        return orderService.placeOrder(userId, orderId);
    }
}
