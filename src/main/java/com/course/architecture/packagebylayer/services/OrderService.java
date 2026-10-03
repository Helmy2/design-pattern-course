package com.course.architecture.packagebylayer.services;

import com.course.architecture.packagebylayer.repositories.OrderRepository;

/**
 * STARTER CODE:
 * OrderService depends on UserService to validate active user.
 */
public class OrderService {
    private final OrderRepository orderRepository;
    private UserService userService;

    public OrderService(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    public void setUserService(UserService userService) {
        this.userService = userService;
    }

    public boolean placeOrder(String userId, String orderId) {
        if (userService != null && userService.isUserActive(userId)) {
            return orderRepository.findOrder(orderId).isPresent();
        }
        return false;
    }

    public void cancelOrdersForUser(String userId) {
        // cancellation logic
    }
}
