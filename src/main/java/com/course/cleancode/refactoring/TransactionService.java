package com.course.cleancode.refactoring;

import java.time.LocalDate;
import java.util.List;

/**
 * STARTER CODE:
 * Call sites must unwrap fields from DateRange.
 */
public class TransactionService {

    public record Transaction(String id, LocalDate date, double amount) {}

    // TODO: Step 3 - Introduce Parameter Object & Preserve Whole Object:
    // Accept DateRange directly instead of separate start and end dates
    public int countTransactionsWithinRange(List<Transaction> transactions, LocalDate start, LocalDate end) {
        int count = 0;
        for (Transaction tx : transactions) {
            if (!tx.date().isBefore(start) && !tx.date().isAfter(end)) {
                count++;
            }
        }
        return count;
    }
}
