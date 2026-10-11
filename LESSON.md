# Lesson 09: Builder Pattern & Modern Kotlin Evolution

> "Separate the construction of a complex object from its representation, allowing the exact same construction process to create different representations step by step."

---

## 🎯 Learning Objectives
1. **The Telescoping Constructor Anti-Pattern**: Overloaded constructors with 4+ permutations lead to positional bugs at the call site and resist cross-field invariant enforcement.
2. **The JavaBeans Anti-Pattern**: Instantiating an empty object and invoking setters sequentially allows objects to escape in an inconsistent, half-initialized state and prevents field immutability (`final`).
3. **The Gang of Four Builder Pattern**:
   - Private constructor in product to prevent direct instantiation via `new`.
   - Static inner `Builder` class with mandatory parameters in its constructor.
   - Fluent method chaining for optional attributes.
   - Terminal `.build()` method asserting cross-field invariants before returning an immutable product.
4. **Modern Kotlin Evolution**:
   - Understand how Kotlin **Named Arguments & Default Parameter Values** eliminate the need for manual Builder classes in domain modeling.

---

## 🏗️ The Problem: Telescoping Constructors in `VacationPackage`
Examine `VacationPackage.java`:
- 4 overloaded constructors where swapping parameters causes silent runtime bugs.
- No central place to enforce invariants: e.g., "start date must be before end date" or "travel insurance requires at least one event ticket".
- Telescoping constructors scale poorly as optional parameters increase.

---

## 📝 Refactoring Tasks (Applying Builder In-Place)
1. **Refactor `VacationPackage` In-Place**:
   - Make the constructor `private VacationPackage(Builder builder)`.
   - Guarantee immutability by wrapping `tickets` with `Collections.unmodifiableList(new ArrayList<>(builder.tickets))`.
2. **Implement Static Inner `Builder`**:
   - Mandatory constructor: `Builder(LocalDate startDate, LocalDate endDate)`. Validate `startDate != null`, `endDate != null`, and `!startDate.isAfter(endDate)`.
   - Fluent setters:
     - `.hotel(String hotel)`
     - `.addTicket(String ticket)`
     - `.travelInsurance(boolean insured)`
   - Terminal `.build()`: Assert that if `travelInsurance` is true, `tickets` cannot be empty!
3. **Inspect Kotlin Alternative**:
   - Check `KotlinVacationConfig.kt` to observe how modern Kotlin solves this natively with default values and named arguments.

---

## 🧪 Verification
Run tests on starter:
```bash
./gradlew test --tests "com.course.patterns.builder.*"
```
Switch to `09-pattern-builder-solution` to review the refactored Builder solution!
