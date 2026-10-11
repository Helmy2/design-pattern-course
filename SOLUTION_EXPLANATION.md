# Solution Explanation: Strategy Pattern & Spring Strategy Map

## 🔍 Key Implementation Decisions

1. **Composition Over Inheritance (`HAS-A` vs. `IS-A`)**:
   - `Duck` does not implement flying or quacking directly. It holds references to `FlyBehavior` and `QuackBehavior`.
   - Adding a new species of duck (e.g. `RubberDuck` with `FlyNoWay` and `Squeak`) requires zero code changes to the base `Duck` class.

2. **Encapsulate What Varies**:
   - The flying algorithm and quacking sound are isolated into separate interface hierarchies:
     - `FlyBehavior`: `FlyWithWings`, `FlyNoWay`, `FlyRocketPowered`.
     - `QuackBehavior`: `Quack`, `Squeak`, `MuteQuack`.

3. **Dynamic Runtime Behavior Swapping**:
   - Because behaviors are held via mutable references, client code can call:
     ```java
     modelDuck.setFlyBehavior(new FlyBehavior.FlyRocketPowered());
     ```
   - This provides runtime flexibility that compile-time inheritance cannot offer.

4. **Modern Enterprise Reality (Spring Strategy Map)**:
   - In Spring Boot, we declare strategy beans implementing a shared interface (`PaymentStrategy`).
   - By injecting `List<PaymentStrategy>` into a service constructor and collecting into a `Map<PaymentProvider, PaymentStrategy>`, we achieve $O(1)$ dispatch without fragile `if/else` checks. Adding a new provider only requires adding a new `@Component` class (Open/Closed Principle).

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.strategy.*"
```
All assertions pass green!
