package com.course.cleancode.refactoring;

import java.time.LocalDate;
import java.util.List;

/**
 * CLEAN CODE SOLUTION:
 * 1. Introduce Parameter Object: DateRange record with invariant assertion.
 * 2. Preserve Whole Object: method takes the DateRange directly.
 */
public class TransactionService {

    public record DateRange(LocalDate start, LocalDate end) {
        public DateRange {
            if (start != null && end != null && start.isAfter(end)) {
                throw new IllegalArgumentException("Start date cannot be after end date");
            }
        }

        public boolean includes(LocalDate date) {
            return !date.isBefore(start) && !date.isAfter(end);
        }
    }

    public record Transaction(String id, LocalDate date, double amount) {}

    public int countTransactionsWithinRange(List<Transaction> transactions, DateRange range) {
        int count = 0;
        for (Transaction tx : transactions) {
            if (range.includes(tx.date())) {
                count++;
            }
        }
        return count;
    }
}
