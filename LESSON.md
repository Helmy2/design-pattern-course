# Lesson 16: Strategy Pattern & Spring Boot Strategy Map

> "Define a family of algorithms, encapsulate each one, and make them interchangeable. Strategy lets the algorithm vary independently from clients that use it."

---

## 🎯 Learning Objectives
1. **Three Foundational OO Design Principles**:
   - **Encapsulate What Varies**: Quacking and flying vary across duck species; separate them from general duck behavior.
   - **Program to Interfaces, Not Implementations**: Duck behavior is represented by polymorphic behavior contracts (`FlyBehavior`, `QuackBehavior`).
   - **Favor Composition Over Inheritance**: Ducks acquire behavior dynamically via `HAS-A` rather than static `IS-A`.
2. **Dynamic Runtime Behavior Swapping**:
   - Changing flying behavior at runtime: a `ModelDuck` unable to fly can be fitted with a `FlyRocketPowered` strategy during execution via `setFlyBehavior()`.
3. **Enterprise Reality — Spring Boot Strategy Map Pattern**:
   - In production backends, avoid giant `switch/case` or `if/else` ladders selecting payment processors or tax engines.
   - Inject all beans implementing `PaymentStrategy` into a `Map<PaymentProvider, PaymentStrategy>` automatically for O(1) dynamic strategy resolution.

---

## 🏗️ The Problem: Rigid Inheritance & Fragile Conditionals
Examine `src/main/java/com/course/patterns/strategy/problem/InflexibleDuckInheritanceProblem.java`:
1. **Flawed Inheritance & Refused Bequest**: Putting `fly()` in a superclass forces `FlawedRubberDuck` to fly across the screen! Subclasses overriding methods to do nothing create code duplication.
2. **Brittle Conditionals**: `NaivePaymentProcessor` relies on hardcoded `if/else` checks, violating the Open/Closed Principle.

To resolve this, we implement the **Strategy Pattern**:
- `FlyBehavior` / `QuackBehavior`: Strategy interfaces encapsulating interchangeable algorithms.
- `Duck.java`: Client context holding behavior references via composition (`HAS-A`).
- `setFlyBehavior(...)`: Allows dynamic runtime swapping of strategies.
- `spring/PaymentStrategyMap.java`: Dynamic Spring strategy map injection eliminating conditional ladders.

---

## 📝 Refactoring Tasks
1. Implement `FlyBehavior` implementations:
   - `FlyWithWings`: "I'm flying!!"
   - `FlyNoWay`: "I can't fly"
   - `FlyRocketPowered`: "I'm flying with a rocket!"
2. Implement `QuackBehavior` implementations:
   - `Quack`: "Quack"
   - `Squeak`: "Squeak"
   - `MuteQuack`: "<< Silence >>"
3. Configure `MallardDuck` and `ModelDuck` with appropriate initial strategies.
4. Implement `CheckoutService` strategy map resolution.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.strategy.*"
```
