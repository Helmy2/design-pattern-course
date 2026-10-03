package com.course.architecture.packagebylayer.services;

import com.course.architecture.packagebylayer.repositories.UserRepository;

/**
 * STARTER CODE:
 * UserService depends back on OrderService to cancel orders when user deleted.
 * Cyclic cross-domain dependency smell!
 */
public class UserService {
    private final UserRepository userRepository;
    private final OrderService orderService;

    public UserService(UserRepository userRepository, OrderService orderService) {
        this.userRepository = userRepository;
        this.orderService = orderService;
    }

    public boolean isUserActive(String userId) {
        return userRepository.findUser(userId).isPresent();
    }

    public void deleteUser(String userId) {
        // Cyclic coupling: UserService calls OrderService
        orderService.cancelOrdersForUser(userId);
    }
}
