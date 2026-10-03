package com.course.cleancode.refactoring;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class RefactoringTechniquesTest {

    @Test
    @DisplayName("Verify InvoicePrinter prints formatted output in starter")
    void testInvoicePrinterStarter() {
        InvoicePrinter printer = new InvoicePrinter();
        InvoicePrinter.Invoice invoice = new InvoicePrinter.Invoice("Tech Corp", 60, 20.0);
        String output = printer.printInvoice(invoice);

        assertThat(output)
                .contains("**** Customer Owes ****")
                .contains("name: Tech Corp")
                .contains("amount: 1290.0");
    }

    @Test
    @DisplayName("Verify PricingCalculator starter calculations")
    void testPricingCalculatorStarter() {
        PricingCalculator bulk = new PricingCalculator(100, 15.0); // 1500 > 1000 -> 0.95
        assertThat(bulk.getPrice()).isEqualTo(1500 * 0.95);

        PricingCalculator regular = new PricingCalculator(10, 50.0); // 500 <= 1000 -> 0.98
        assertThat(regular.getPrice()).isEqualTo(500 * 0.98);
    }

    @Test
    @DisplayName("Verify TransactionService starter counting")
    void testTransactionServiceStarter() {
        TransactionService service = new TransactionService();
        LocalDate now = LocalDate.now();
        List<TransactionService.Transaction> txs = List.of(
                new TransactionService.Transaction("T1", now.minusDays(5), 100.0),
                new TransactionService.Transaction("T2", now.minusDays(2), 200.0),
                new TransactionService.Transaction("T3", now.plusDays(2), 300.0)
        );

        int count = service.countTransactionsWithinRange(txs, now.minusDays(7), now);
        assertThat(count).isEqualTo(2);
    }

    @Test
    @DisplayName("Verify EmployeeCompensation switch ladder in starter")
    void testEmployeeCompensationStarter() {
        EmployeeCompensation.Employee eng = new EmployeeCompensation.Employee(
                EmployeeCompensation.EmployeeType.ENGINEER, 100_000.0
        );
        assertThat(eng.calculateBonus()).isEqualTo(10_000.0);

        EmployeeCompensation.Employee mgr = new EmployeeCompensation.Employee(
                EmployeeCompensation.EmployeeType.MANAGER, 120_000.0
        );
        assertThat(mgr.calculateBonus()).isEqualTo(30_000.0);

        EmployeeCompensation.Employee exec = new EmployeeCompensation.Employee(
                EmployeeCompensation.EmployeeType.EXECUTIVE, 200_000.0
        );
        assertThat(exec.calculateBonus()).isEqualTo(100_000.0);
    }
}
