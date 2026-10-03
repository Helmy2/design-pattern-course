package com.course.architecture.packagebylayer.controllers;

import com.course.architecture.packagebylayer.services.OrderService;

public class OrderController {
    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    public boolean handleOrder(String userId, String orderId) {
        return orderService.placeOrder(userId, orderId);
    }
}
