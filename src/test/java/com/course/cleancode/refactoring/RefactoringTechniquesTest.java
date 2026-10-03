package com.course.cleancode.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class RefactoringTechniquesTest {

    @Test
    @DisplayName("Verify InvoicePrinter with Extract Method and Extract Variable")
    void testInvoicePrinterClean() {
        InvoicePrinter printer = new InvoicePrinter();
        InvoicePrinter.Invoice invoice = new InvoicePrinter.Invoice("Tech Corp", 60, 20.0);
        String output = printer.printInvoice(invoice);

        assertThat(output)
                .contains("**** Customer Owes ****")
                .contains("name: Tech Corp")
                .contains("amount: 1290.0");
    }

    @Test
    @DisplayName("Verify PricingCalculator with Replace Temp with Query")
    void testPricingCalculatorCleanQueries() {
        PricingCalculator bulk = new PricingCalculator(100, 15.0);
        assertThat(bulk.basePrice()).isEqualTo(1500.0);
        assertThat(bulk.discountFactor()).isEqualTo(0.95);
        assertThat(bulk.getPrice()).isEqualTo(1500.0 * 0.95);

        PricingCalculator regular = new PricingCalculator(10, 50.0);
        assertThat(regular.basePrice()).isEqualTo(500.0);
        assertThat(regular.discountFactor()).isEqualTo(0.98);
        assertThat(regular.getPrice()).isEqualTo(500.0 * 0.98);
    }

    @Test
    @DisplayName("Verify TransactionService with Parameter Object & Preserve Whole Object")
    void testTransactionServiceDateRange() {
        TransactionService service = new TransactionService();
        LocalDate now = LocalDate.now();
        List<TransactionService.Transaction> txs = List.of(
                new TransactionService.Transaction("T1", now.minusDays(5), 100.0),
                new TransactionService.Transaction("T2", now.minusDays(2), 200.0),
                new TransactionService.Transaction("T3", now.plusDays(2), 300.0)
        );

        TransactionService.DateRange validRange = new TransactionService.DateRange(now.minusDays(7), now);
        int count = service.countTransactionsWithinRange(txs, validRange);
        assertThat(count).isEqualTo(2);

        // Invariant check
        assertThatThrownBy(() -> new TransactionService.DateRange(now.plusDays(1), now))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("Verify EmployeeCompensation with Polymorphism replacing switch")
    void testEmployeeCompensationPolymorphism() {
        EmployeeCompensation.Employee eng = new EmployeeCompensation.Engineer(100_000.0);
        assertThat(eng.calculateBonus()).isEqualTo(10_000.0);

        EmployeeCompensation.Employee mgr = new EmployeeCompensation.Manager(120_000.0);
        assertThat(mgr.calculateBonus()).isEqualTo(30_000.0);

        EmployeeCompensation.Employee exec = new EmployeeCompensation.Executive(200_000.0);
        assertThat(exec.calculateBonus()).isEqualTo(100_000.0);
    }
}
