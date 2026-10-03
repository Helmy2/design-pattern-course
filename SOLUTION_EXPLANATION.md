# Solution Explanation: Core Refactoring Techniques

## 🔍 Key Refactoring Decisions

1. **Extract Method & Extract Variable (`InvoicePrinter`)**:
   - Decomposed a monolithic method into three single-purpose methods:
     - `printBanner()`
     - `calculateTotal(invoice)`
     - `printDetails(name, total)`
   - Used **Extract Variable** to give clear business semantics to sub-calculations:
     - `basePrice = quantity * itemPrice`
     - `quantityDiscount = Math.max(0, quantity - 50) * itemPrice * 0.05`
     - `shippingFee = Math.min(basePrice * 0.1, 100.0)`

2. **Replace Temp with Query (`PricingCalculator`)**:
   - Replaced mutable temporary variables with private, query methods:
     - `basePrice()`
     - `discountFactor()`
   - Makes `getPrice()` an expressive formula: `basePrice() * discountFactor()`.

3. **Introduce Parameter Object & Preserve Whole Object (`TransactionService`)**:
   - Created `record DateRange(LocalDate start, LocalDate end)` with domain invariant assertion (`start <= end`).
   - Encapsulated range membership logic in `dateRange.includes(date)`.
   - Method signature now accepts `DateRange` directly instead of forcing call sites to unwrap start/end dates.

4. **Replace Conditional with Polymorphism (`EmployeeCompensation`)**:
   - Replaced `switch (type)` with an abstract base class `Employee` and subclasses:
     - `Engineer` (10% bonus)
     - `Manager` (25% bonus)
     - `Executive` (50% bonus)
   - Adheres to OCP: adding new employee tiers no longer touches existing calculation logic.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.cleancode.refactoring.*"
```
All assertions pass green!
