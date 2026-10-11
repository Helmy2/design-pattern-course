# Lesson 16: Strategy Pattern & Spring Strategy Map

> "Defines a family of algorithms, encapsulates each one, and makes them interchangeable. Strategy lets the algorithm vary independently from clients that use it."

---

## 🎯 Learning Objectives
1. **Encapsulate What Varies**: Separate flying and quacking behaviors from the `Duck` superclass.
2. **Favor Composition Over Inheritance (`HAS-A` vs. `IS-A`)**: Compose behavior interfaces rather than inheriting rigid implementations.
3. **Program to an Interface, Not an Implementation**: Depend on `FlyBehavior` and `QuackBehavior`.
4. **Dynamic Runtime Behavior Swapping**: Change behaviors on the fly via setter injection (`setFlyBehavior()`).
5. **Modern Enterprise Reality — Spring Strategy Map**:
   - Inject a list of strategy beans (`List<PaymentStrategy>`) into a service and collect into a `Map<PaymentProvider, PaymentStrategy>`.
   - Dispatch requests in $O(1)$ without brittle `if/else` checks, satisfying the Open/Closed Principle.

---

## 🏗️ The Problem: Flawed Inheritance & Rigid If/Else in Starter
Examine `Duck.java` and `PaymentProcessor.java`:
- `Duck.java` implements `fly()` and `quack()` in the base superclass. Subclasses like `RubberDuck` inherit `fly()` and fly across the screen!
- `PaymentProcessor.java` processes payments using brittle `if/else` chains. Adding new providers forces modifying existing code (violating OCP).

To resolve this, we introduce the **Strategy Pattern**:
- Create `FlyBehavior` and `QuackBehavior` interface hierarchies.
- Refactor `Duck` to compose behaviors and provide `performFly()` and `performQuack()`.
- Implement `PaymentStrategyMap` with dynamic map-based dispatch.

---

## 📝 Refactoring Tasks
1. Create `FlyBehavior` and `QuackBehavior` interfaces with concrete strategy implementations.
2. Refactor `Duck.java` to hold behavior references via composition.
3. Create `ModelDuck.java` demonstrating runtime strategy swapping.
4. Create `spring/PaymentStrategyMap.java` replacing `if/else` with Spring strategy map dispatch.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.strategy.*"
```
