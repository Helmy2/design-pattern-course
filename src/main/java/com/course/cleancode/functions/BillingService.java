package com.course.cleancode.functions;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER CODE:
 * Flag argument smell: boolean argument controls two completely different business branches.
 */
public class BillingService {

    public record Invoice(String id, double amount, String customerEmail, String customerPhone) {}

    private final List<String> auditLogs = new ArrayList<>();

    // TODO: Step 2 - Eliminate flag argument: split into processUrgentInvoice and processStandardInvoice
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
