# Lesson 09: Builder Pattern & Modern Kotlin Evolution

> "Separate the construction of a complex object from its representation, allowing the exact same construction process to create different representations step by step."

---

## 🎯 Learning Objectives
1. **The Telescoping Constructor Anti-Pattern**: Overloaded constructors with 4+ permutations lead to positional bugs at the call site.
2. **The JavaBeans Anti-Pattern**: Instantiating an empty object and invoking setters sequentially allows objects to escape in an inconsistent, half-initialized state and prevents field immutability (`final`).
3. **The Gang of Four Builder Pattern**:
   - Private constructor in product to prevent direct instantiation via `new`.
   - Static inner `Builder` class with mandatory parameters in its constructor.
   - Fluent method chaining for optional attributes.
   - Terminal `.build()` method asserting cross-field invariants before returning an immutable product.
4. **Modern Kotlin Evolution**:
   - Understand how Kotlin **Named Arguments & Default Parameter Values** eliminate the need for manual Builder classes in domain modeling.

---

## 🏗️ The Problem: Telescoping & Mutable Inconsistency
Examine `src/main/java/com/course/patterns/builder/`:
- `TelescopingVacationPackage.java`: 5 overloaded constructors where swapping parameters causes silent runtime bugs.
- `VacationPackage.java`: Needs a fluent static inner `Builder` class that validates date ordering and ensures travel insurance cannot be purchased without event tickets.

---

## 📝 Refactoring Tasks
1. Implement static inner `Builder` inside `VacationPackage`:
   - Mandatory constructor: `Builder(LocalDate startDate, LocalDate endDate)`. Validate `startDate <= endDate`.
   - Fluent setters: `.hotel(String hotel)`, `.addTicket(String ticket)`, `.travelInsurance(boolean insured)`.
   - Terminal `.build()`: Assert that if `travelInsurance` is true, `tickets` cannot be empty.
2. Inspect `KotlinVacationConfig.kt` to observe how Kotlin achieves fluent named configuration natively.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.builder.*"
```
