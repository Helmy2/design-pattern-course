# Lesson 11: Adapter Pattern & Hexagonal Architecture (Ports and Adapters)

> "Converts the interface of a class into another interface that clients expect. Adapter lets classes work together that couldn't otherwise because of incompatible interfaces."

---

## 🎯 Learning Objectives
1. **The Object Adapter (Composition)**: Wrap an incompatible adaptee (`Turkey`) inside an adapter that implements the target interface (`Duck`).
2. **Object Adapter vs. Class Adapter**:
   - Object Adapter uses **Composition** (`HAS-A`) and works with any subclass of the adaptee.
   - Class Adapter relies on multiple class inheritance (not possible in Java).
3. **Legacy API Adaptation**: Adapting legacy JDK `Enumeration` to modern `Iterator`.
4. **Enterprise Scale — Hexagonal Architecture (Ports and Adapters)**:
   - **Driven Port**: Domain core defines abstract interfaces (e.g. `PaymentGatewayPort`).
   - **Driven Adapter**: Infrastructure layer implements the port, wrapping third-party vendor SDKs (e.g. `ThirdPartyStripeSdk`) so external vendor changes never pollute the domain core.

---

## 🏗️ The Problem: Incompatible Interfaces in Starter
- **Duck vs. Turkey**:
  - `DuckSimulator` expects a `Duck` (`quack()`, `fly()`).
  - Third-party vendor supplies `Turkey` (`gobble()`, `fly()` short distance).
  - Passing a `Turkey` directly into `DuckSimulator` fails compilation because the interfaces are incompatible.
- **Hexagonal Domain Port vs. Vendor SDK**:
  - Domain core defines `PaymentGatewayPort` with `charge(String customerId, double amount)`.
  - External vendor SDK `ThirdPartyStripeSdk` requires `createCharge(String customerId, long cents, String currency)` with amounts in cents.
  - The domain cannot directly depend on proprietary vendor SDK structures without violating Clean Architecture and DIP.

To resolve these incompatibilities, we implement the **Adapter Pattern**:
- `TurkeyAdapter.java`: Implements `Duck` and composes `Turkey`. Translates `quack()` to `turkey.gobble()` and repeats short bursts of `fly()` 5 times.
- `hexagonal/StripePaymentAdapter.java`: Implements `PaymentGatewayPort` and composes `ThirdPartyStripeSdk`.

---

## 📝 Refactoring Tasks
1. Create `TurkeyAdapter` implementing `Duck` and wrapping `Turkey`.
2. Inspect `EnumerationIterator` adapting legacy `java.util.Enumeration` to `java.util.Iterator`.
3. Create `StripePaymentAdapter` implementing `PaymentGatewayPort` and wrapping `ThirdPartyStripeSdk`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.adapter.*"
```
