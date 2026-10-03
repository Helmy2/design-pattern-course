package com.course.architecture.packagebyfeature.order;

import java.util.Optional;

public interface OrderRepository {
    Optional<String> findOrder(String orderId);
}
