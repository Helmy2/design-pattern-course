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
   - **Driven Adapter**: Infrastructure layer implements the port, wrapping third-party vendor SDKs (e.g. Stripe, PayPal, Fawry) so external vendor changes never pollute the domain core.

---

## 🏗️ The Problem: Incompatible Interfaces in Starter
Examine `src/main/java/com/course/patterns/adapter/problem/IncompatibleInterfaceProblem.java`:
- Client systems expecting `Duck` (`quack()`, `fly()`) cannot interact with third-party vendors supplying `Turkey` (`gobble()`, `fly()`).
- In enterprise applications, domain ports like `PaymentGatewayPort` cannot bind directly to external third-party SDKs like Stripe without corrupting domain models with third-party types.

To resolve this, we implement the **Adapter Pattern**:
- `TurkeyAdapter.java`: Translates `quack()` to `turkey.gobble()` and repeats short bursts of `fly()` to mimic a duck.
- `hexagonal/StripePaymentAdapter.java`: Driven adapter wrapping `ThirdPartyStripeSdk` to satisfy `PaymentGatewayPort`.

---

## 📝 Refactoring Tasks
1. Implement `TurkeyAdapter` implementing `Duck` and wrapping `Turkey`.
2. Implement `EnumerationIterator` adapting legacy `java.util.Enumeration` to `java.util.Iterator`.
3. Implement `StripePaymentAdapter` implementing `PaymentGatewayPort`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.adapter.*"
```
