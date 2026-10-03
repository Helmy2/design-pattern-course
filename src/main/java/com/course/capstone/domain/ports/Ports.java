package com.course.capstone.domain.ports;

import com.course.capstone.domain.DomainValueObjects.Money;
import com.course.capstone.domain.Order;

public interface Ports {

    public interface PaymentGatewayPort {
        enum Provider { STRIPE, PAYPAL }
        Provider getProvider();
        boolean charge(String customerId, Money amount);
    }

    public interface NotificationPort {
        void sendOrderConfirmation(String email, Money total);
    }

    public interface OrderRepositoryPort {
        void save(Order order);
    }
}
