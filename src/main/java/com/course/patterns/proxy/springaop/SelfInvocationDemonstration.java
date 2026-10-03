package com.course.patterns.proxy.springaop;

import java.util.ArrayList;
import java.util.List;

/**
 * Educational Demonstration:
 * Explains how Spring's dynamic proxies wrap services, and why "self-invocation"
 * (calling an annotated method from within the same class) fails to trigger proxy interceptors.
 */
public class SelfInvocationDemonstration {

    public interface OrderService {
        void processOrder(String orderId);
        void saveWithAudit(String orderId);
        List<String> getAuditLog();
    }

    public static class RawOrderService implements OrderService {
        private final List<String> auditLog = new ArrayList<>();

        @Override
        public void processOrder(String orderId) {
            // Direct method call bypasses any proxy wrapper on 'this'!
            saveWithAudit(orderId);
        }

        @Override
        public void saveWithAudit(String orderId) {
            auditLog.add("SAVED_WITH_AUDIT:" + orderId);
        }

        @Override
        public List<String> getAuditLog() {
            return List.copyOf(auditLog);
        }
    }

    /**
     * Simulated Spring Dynamic Proxy Interceptor
     */
    public static class AuditingProxy implements OrderService {
        private final OrderService delegate;
        private final List<String> proxyInterceptions = new ArrayList<>();

        public AuditingProxy(OrderService delegate) {
            this.delegate = delegate;
        }

        @Override
        public void processOrder(String orderId) {
            proxyInterceptions.add("INTERCEPT_TRANSACTION_START:processOrder");
            delegate.processOrder(orderId);
            proxyInterceptions.add("INTERCEPT_TRANSACTION_COMMIT:processOrder");
        }

        @Override
        public void saveWithAudit(String orderId) {
            proxyInterceptions.add("INTERCEPT_AUDIT_TRANSACTION:saveWithAudit");
            delegate.saveWithAudit(orderId);
        }

        @Override
        public List<String> getAuditLog() {
            return delegate.getAuditLog();
        }

        public List<String> getProxyInterceptions() {
            return List.copyOf(proxyInterceptions);
        }
    }
}
