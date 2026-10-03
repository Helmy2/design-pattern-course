# Solution Explanation: Functions & Command-Query Separation (CQS)

## 🔍 Key Refactoring Decisions

1. **Parameter Objects (Kills Polyadic Arguments)**:
   - Instead of 8 loose primitive arguments (`query`, `minPrice`, `maxPrice`, `startDate`, `endDate`, `inStockOnly`, `pageNumber`, `pageSize`), we created cohesive domain records:
     - `PriceRange(Double min, Double max)` (validates `min <= max`)
     - `DateRange(LocalDate start, LocalDate end)` (validates `start <= end`)
     - `ProductSearchCriteria(query, priceRange, dateRange, inStockOnly)`
     - `PageRequest(pageNumber, pageSize)`
   - The method signature was reduced from 8 parameters to 2 cohesive parameter objects:
     `searchProducts(ProductSearchCriteria criteria, PageRequest pageRequest)`.

2. **Eliminating Flag Arguments**:
   - Instead of `billingService.processInvoice(invoice, true)` (where the caller has to decipher what `true` means), we split the logic into two self-documenting methods:
     - `processUrgentInvoice(Invoice invoice)`
     - `processStandardInvoice(Invoice invoice)`

3. **Command-Query Separation (CQS)**:
   - The method `checkAndEvictIfExpired(key)` violated CQS by reading cache state and mutating it at the same time.
   - Refactored into:
     - Query: `boolean isExpired(String sessionKey)` (returns boolean, idempotent, no side effects).
     - Command: `void evictSession(String sessionKey)` (returns void, alters cache state).
   - Call site becomes crystal clear:
     ```java
     if (sessionManager.isExpired(sessionId)) {
         sessionManager.evictSession(sessionId);
     }
     ```

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.cleancode.functions.*"
```
All assertions pass green!
