package com.course.cleancode.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER ANTI-PATTERN: Flag Argument Smell
 * A boolean argument controls two completely different execution paths,
 * forcing the function to do more than one thing.
 */
public class BillingService {

    public record Invoice(String id, double amount, String customerEmail, String customerPhone) {}

    private final List<String> auditLogs = new ArrayList<>();

    public void processInvoice(Invoice invoice, boolean isUrgent) {
        if (isUrgent) {
            auditLogs.add("CHARGED_IMMEDIATE:" + invoice.amount());
            auditLogs.add("SMS_SENT:" + invoice.customerPhone());
        } else {
            auditLogs.add("BATCH_QUEUED:" + invoice.amount());
            auditLogs.add("EMAIL_SENT:" + invoice.customerEmail());
        }
    }

    public List<String> getAuditLogs() {
        return List.copyOf(auditLogs);
    }
}
