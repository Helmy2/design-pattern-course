# Lesson 03: Common Code Smells & Antipatterns

> "Code smells are diagnostic symptoms in source code that indicate deeper design problems without necessarily being operational bugs."

---

## 🎯 Learning Objectives
1. **Bloaters - Primitive Obsession**: Stop passing unvalidated primitives (`String email`, `String phone`, `String zipCode`) across multiple service layers. Encapsulate validation inside **Domain Value Objects** (`record Email`, `record PhoneNumber`).
2. **OO Abusers - Refused Bequest (LSP Violation)**: Subclasses shouldn't inherit methods they don't support or override them to throw `UnsupportedOperationException` (e.g. `Penguin.fly()`). Segregate interfaces (`Bird` vs `FlyingBird`).
3. **OO Abusers - Switch on Type Codes**: Replace conditional `switch (customerType)` branching with polymorphic Strategy implementations (`DiscountPolicy`).
4. **Couplers - Feature Envy**: Stop Class A from reaching into Class B's internals to perform calculations (`OrderReceiptPrinter` calculating order total). Apply **Tell, Don't Ask**.
5. **Couplers - Message Chains (Law of Demeter Violation)**: Avoid fragile dot navigation `order.getCustomer().getProfile().getAddress().getCountry().getCode()`. Delegate through immediate owners.

---

## 🏗️ The Problem: Smelly Code in Starter
Examine `src/main/java/com/course/cleancode/smells/`:
- `UserRegistrationService.java`: Accepts raw strings, repeating regex checks in multiple methods.
- `DiscountCalculator.java`: Massive switch statement over `CustomerType` (REGULAR, PREMIUM, VIP).
- `BirdHierarchy.java`: `Penguin` inherits `fly()` and throws `UnsupportedOperationException`.
- `OrderReceiptPrinter.java`: Extracts raw items and tax rates from `Order` to calculate totals manually, and navigates deep message chains.

---

## 📝 Refactoring Tasks
1. Refactor `UserRegistrationService` to use domain value objects: `Email`, `PhoneNumber`.
2. Refactor `DiscountCalculator` using polymorphic `DiscountPolicy` (`RegularDiscount`, `PremiumDiscount`, `VipDiscount`).
3. Segregate `Bird` and `FlyingBird` interfaces so `Penguin` implements only `Bird`.
4. Relocate calculation logic into `Order.calculateTotalWithTax()` (Tell, Don't Ask) and provide `order.getDeliveryCountryCode()` to satisfy the Law of Demeter.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.cleancode.smells.*"
```
