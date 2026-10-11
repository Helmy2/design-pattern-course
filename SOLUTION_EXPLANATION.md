# Solution Explanation: Adapter Pattern & Hexagonal Architecture

## 🔍 Key Implementation Decisions

1. **Object Adapter via Composition**:
   - `TurkeyAdapter` implements the target `Duck` interface and delegates requests to the adaptee `Turkey`.
   - Adapting method signatures:
     - `quack()` translates directly to `turkey.gobble()`.
     - `fly()` compensates for the fact that turkeys only fly short bursts by invoking `turkey.fly()` 5 times in a loop.
   - The client (`DuckSimulator.simulate(Duck duck)`) can treat the adapted Turkey identically to any native Duck without altering client code (Open/Closed Principle).

2. **Object Adapter vs. Class Adapter**:
   - **Object Adapter**: Uses **Composition** (`HAS-A`). Highly flexible; adapts an adaptee and any of its future subclasses.
   - **Class Adapter**: Uses **Multiple Inheritance** (`IS-A` both Target and Adaptee). Not possible in Java for classes without interface mixins.

3. **Enterprise Hexagonal Architecture (Ports and Adapters)**:
   - Scales the Adapter pattern across the entire system:
     - **Driven Port (`PaymentGatewayPort`)**: Domain core defines what it needs to process payments, completely isolated from external frameworks or vendor SDKs.
     - **Driven Adapter (`StripePaymentAdapter`)**: Encapsulates vendor-specific complexities (converting dollars to cents, invoking `ThirdPartyStripeSdk`, translating proprietary response into domain `PaymentConfirmation` record).
   - If the company swaps Stripe for PayPal or Adyen, the core domain remains 100% untouched.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.adapter.*"
```
All assertions pass green!
