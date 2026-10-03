# Lesson 04: Core Refactoring Techniques

> "Refactoring is the process of improving code structure and internal design without altering its observable runtime behavior."

---

## 🎯 Learning Objectives
1. **Extract Method**: Break long procedures into small, well-named helper methods that read at a single level of abstraction.
2. **Extract Variable**: Replace complex inline arithmetic and conditional boolean expressions with descriptive explanatory variables.
3. **Replace Temp with Query**: Eliminate local mutable variables holding calculated intermediate values by turning them into private query methods.
4. **Introduce Parameter Object & Preserve Whole Object**: Pass cohesive domain abstractions (`DateRange`) instead of forcing call sites to unwrap and pass individual fields.
5. **Replace Conditional with Polymorphism**: Eliminate switch statements checking type codes by subclassing and overriding an abstract calculation method.

---

## 🏗️ The Problem: Unrefactored Code in Starter
Examine `src/main/java/com/course/cleancode/refactoring/`:
- `InvoicePrinter.java`: Monolithic procedure mixing banner printing, math formulas, and detail printing.
- `PricingCalculator.java`: Uses temporary variables `basePrice` and `discountFactor` instead of queries.
- `TransactionService.java`: Takes separate `start` and `end` dates, forcing callers to unpack fields.
- `EmployeeCompensation.java`: Switch statement determining bonuses based on `EmployeeType` enum.

---

## 📝 Refactoring Tasks
1. Refactor `InvoicePrinter` by extracting:
   - `printBanner()`
   - `calculateTotal(invoice)` (using extracted variables `basePrice`, `quantityDiscount`, `shippingFee`)
   - `printDetails(customerName, total)`
2. In `PricingCalculator`, replace temp variables with private query methods `basePrice()` and `discountFactor()`.
3. In `TransactionService`, create `record DateRange(LocalDate start, LocalDate end)` and preserve the whole object.
4. In `EmployeeCompensation`, refactor `Employee` into an abstract class with `Engineer`, `Manager`, and `Executive` subclasses.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.cleancode.refactoring.*"
```
