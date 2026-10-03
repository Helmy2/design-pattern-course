# Lesson 02: Functions, Arguments & Command-Query Separation (CQS)

> "Functions are the fundamental building blocks of system behavior. They should do one thing, do it well, and do it only."

---

## 🎯 Learning Objectives
1. **Small & Focused**: Functions should rarely exceed 15–20 lines with clear boundaries.
2. **Single Level of Abstraction (SLA)**: All statements in a method should be at the same level of abstraction.
3. **Parameter Objects**: Polyadic methods (4+ arguments) are fragile. Bundle cohesive parameters into domain records (`ProductSearchCriteria`, `PriceRange`, `DateRange`).
4. **Eliminate Flag Arguments**: A boolean parameter screams that a function does at least two different things. Split into two clearly named functions (`processUrgentInvoice` vs `processStandardInvoice`).
5. **Command-Query Separation (CQS)**: A method should either perform an action (mutate state / return void) or answer a question (query state without side effects), never both.
6. **DRY (Don't Repeat Yourself)**: Eliminate duplicated logic that risks divergent bugs.

---

## 🏗️ The Problem: Function Code Smells
Examine `src/main/java/com/course/cleancode/functions/`:
- `ProductCatalogService.java`: `searchProducts(...)` takes 8 positional arguments! Easy to accidentally swap `minPrice` with `maxPrice` or `startDate` with `endDate`.
- `BillingService.java`: `processInvoice(Invoice invoice, boolean isUrgent)` uses a boolean flag to choose between immediate charging + SMS alerts vs batch queuing + email.
- `SessionCacheManager.java`: `checkAndEvictIfExpired(String sessionKey)` simultaneously checks expiration and silently deletes from cache, confusing call sites.

---

## 📝 Refactoring Tasks
1. **Parameter Objects**:
   - Create `record PriceRange(Double min, Double max)` and `record DateRange(LocalDate start, LocalDate end)`.
   - Bundle into `record ProductSearchCriteria(String query, PriceRange priceRange, DateRange dateRange, boolean inStockOnly)`.
   - Refactor `searchProducts` to accept `(ProductSearchCriteria criteria, int page, int pageSize)`.
2. **Eliminate Flag Argument**:
   - Split `processInvoice(invoice, isUrgent)` into `processUrgentInvoice(Invoice invoice)` and `processStandardInvoice(Invoice invoice)`.
3. **Command-Query Separation**:
   - Replace `checkAndEvictIfExpired(key)` with two distinct methods:
     - Query: `boolean isExpired(String sessionKey)` (side-effect free).
     - Command: `void evictSession(String sessionKey)` (mutates state, returns void).

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.cleancode.functions.*"
```
