# Solution Explanation: Builder Pattern & Modern Kotlin Evolution

## 🔍 Key Implementation Decisions

1. **In-Place Refactoring from Telescoping Anti-Pattern**:
   - In the starter branch, `VacationPackage` relied on 4 overloaded telescoping constructors.
   - Positional parameters caused fragility at the call site and prevented clean cross-field invariant enforcement.
   - In this solution, `VacationPackage` is refactored in-place to use a **static inner `Builder`**.

2. **Core Guarantees of the GoF Builder Pattern**:
   - **Private Constructor**: Prevents arbitrary instantiation via `new` outside the Builder.
   - **Mandatory vs. Optional Separation**: Mandatory parameters (`startDate`, `endDate`) are required by `Builder(start, end)`, while optional attributes (`hotel`, `addTicket`, `travelInsurance`) use fluent chaining.
   - **Deferred Invariant Enforcement**: Cross-field checks (e.g. `travelInsurance` requires non-empty `tickets`) run atomically inside `.build()` before the constructed instance escapes.
   - **True Immutability**: All fields are `final`, and collections are wrapped with `Collections.unmodifiableList()`.

3. **Modern JVM / Kotlin Evolution**:
   - In Kotlin, domain classes rarely need the GoF Builder pattern because the language natively supports:
     1. **Named Arguments**: Self-documenting call sites without positional confusion.
     2. **Default Parameter Values**: Eliminates constructor overloading.
     3. **`init` Invariant Blocks**: Validates rules during instantiation.
   - See `KotlinVacationConfig.kt` for the idiomatic Kotlin alternative.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.builder.*"
```
All assertions pass green!
