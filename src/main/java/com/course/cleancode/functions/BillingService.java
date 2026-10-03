package com.course.cleancode.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * Eliminates flag arguments by providing two explicitly named methods.
 * Call sites are self-documenting.
 */
public class BillingService {

    public record Invoice(String id, double amount, String customerEmail, String customerPhone) {}

    private final List<String> auditLogs = new ArrayList<>();

    public void processUrgentInvoice(Invoice invoice) {
        auditLogs.add("CHARGED_IMMEDIATE:" + invoice.amount());
        auditLogs.add("SMS_SENT:" + invoice.customerPhone());
    }

    public void processStandardInvoice(Invoice invoice) {
        auditLogs.add("BATCH_QUEUED:" + invoice.amount());
        auditLogs.add("EMAIL_SENT:" + invoice.customerEmail());
    }

    public List<String> getAuditLogs() {
        return List.copyOf(auditLogs);
    }
}
