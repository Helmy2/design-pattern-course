# Solution Explanation: Common Code Smells & Antipatterns

## 🔍 Key Refactoring Decisions

1. **Replacing Primitive Obsession with Domain Value Objects**:
   - Instead of passing raw strings `String email`, `String phoneNumber` into services and repeating regex assertions, we introduced:
     - `record Email(String value)`
     - `record PhoneNumber(String value)`
   - Validation occurs automatically in the compact record constructor, making illegal states unrepresentable.

2. **Fixing Switch over Type Codes via Polymorphism (Strategy)**:
   - `switch (customerType)` was replaced by a clean `DiscountPolicy` interface:
     - `RegularDiscount`
     - `PremiumDiscount`
     - `VipDiscount`
   - Adheres to the **Open/Closed Principle (OCP)**: Adding an `EmployeeDiscount` requires creating a new class rather than reopening and modifying existing switch cases.

3. **Fixing Refused Bequest (Liskov Substitution Principle)**:
   - Forcing `Penguin` to inherit an abstract `fly()` method and throwing `UnsupportedOperationException` violated LSP.
   - We segregated the interfaces:
     - `Bird` defines `eat()`.
     - `FlyingBird` extends `Bird` and defines `fly()`.
   - `Eagle` implements `FlyingBird`, while `Penguin` implements only `Bird`. Subtypes can now be used wherever their base type is expected without runtime surprises.

4. **Eliminating Feature Envy (Tell, Don't Ask)**:
   - `OrderReceiptPrinter` reached into `Order` to sum items and apply customer taxes.
   - We moved `calculateTotalWithTax()` directly into `Order`, where the data lives.

5. **Eliminating Message Chains (Law of Demeter)**:
   - Avoided the train wreck `order.getCustomer().getAddress().getCountry().getCode()`.
   - Introduced intermediary delegation methods:
     `order.getDeliveryCountryCode()` ➔ `customer.getDeliveryCountryCode()` ➔ `address.getCountryCode()`.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.cleancode.smells.*"
```
All assertions pass green!
