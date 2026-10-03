package com.course.capstone.application;

import com.course.capstone.domain.DomainValueObjects.Money;
import com.course.capstone.domain.Order;
import com.course.capstone.domain.TaxPolicy;
import com.course.capstone.domain.ports.Ports.NotificationPort;
import com.course.capstone.domain.ports.Ports.OrderRepositoryPort;
import com.course.capstone.domain.ports.Ports.PaymentGatewayPort;

import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * CLEAN ARCHITECTURE APPLICATION SERVICE:
 * Orchestrates domain entities and driven ports adhering to SRP, OCP, and DIP.
 */
public class OrderFulfillmentService {

    private final Map<PaymentGatewayPort.Provider, PaymentGatewayPort> paymentGateways;
    private final NotificationPort notificationPort;
    private final OrderRepositoryPort orderRepository;
    private final TaxPolicy taxPolicy;

    public OrderFulfillmentService(
            List<PaymentGatewayPort> gateways,
            NotificationPort notificationPort,
            OrderRepositoryPort orderRepository,
            TaxPolicy taxPolicy
    ) {
        this.paymentGateways = gateways.stream()
                .collect(Collectors.toMap(PaymentGatewayPort::getProvider, Function.identity()));
        this.notificationPort = notificationPort;
        this.orderRepository = orderRepository;
        this.taxPolicy = taxPolicy;
    }

    public boolean fulfillOrder(Order order, PaymentGatewayPort.Provider provider) {
        double taxRate = taxPolicy.getTaxRate(order.getCountryCode());
        Money total = order.calculateTotal(taxRate);

        PaymentGatewayPort gateway = paymentGateways.get(provider);
        if (gateway == null) {
            throw new IllegalArgumentException("Unsupported payment provider: " + provider);
        }

        boolean charged = gateway.charge(order.getCustomerEmail().value(), total);
        if (charged) {
            orderRepository.save(order);
            notificationPort.sendOrderConfirmation(order.getCustomerEmail().value(), total);
            return true;
        }
        return false;
    }
}
