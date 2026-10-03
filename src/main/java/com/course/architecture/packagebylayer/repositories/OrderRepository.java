package com.course.architecture.packagebylayer.repositories;

import java.util.Optional;

public interface OrderRepository {
    Optional<String> findOrder(String orderId);
}
