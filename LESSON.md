# Lesson 12: Decorator Pattern & Kotlin Interface Delegation

> "Attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality."

---

## 🎯 Learning Objectives
1. **The Class Explosion Problem**: Avoid creating dozens of static subclasses (`HouseBlendWithMochaAndWhip`) to represent combinations of optional features.
2. **Open/Closed Principle (OCP)**: Add new behavior (condiments, middleware, auditing) without altering tested base classes.
3. **Runtime Composition & Delegation**:
   - Decorators have the **same supertype** as the objects they decorate.
   - Decorators hold a reference to the wrapped component via **composition**.
   - Invocation propagates through the chain: `Whip.cost() ➔ Mocha.cost() ➔ DarkRoast.cost()`.
4. **Real-World Java I/O (`java.io`)**: Understand how `BufferedInputStream` decorates `FileInputStream`, and implement a custom `LowerCaseInputStream`.
5. **Modern Kotlin Interface Delegation (`by`)**:
   - In Java, decorating an interface with 10 methods forces manual delegation boilerplate for all 10.
   - In Kotlin, `by delegate` handles all forwarding automatically at the compiler level.

---

## 🏗️ The Problem: Subclass Explosion & Boolean Superclass Flags
Examine `src/main/java/com/course/patterns/decorator/problem/BeverageInheritanceProblem.java`:
1. **Subclass Explosion**: Adding combinations manually requires dozens of static classes (`DarkRoastWithMochaAndWhip`).
2. **Boolean Flags in Base Class**: Using flags like `hasMocha`, `hasWhip` breaks OCP (condiment price changes alter the base class), cannot support double mocha, and forces unrelated drinks to inherit unneeded condiments.

To solve this, we implement the **Decorator Pattern**:
- `Beverage.java`: Abstract Component (`description`, `cost()`).
- `CondimentDecorator.java`: Abstract decorator superclass.
- `Mocha.java`, `Whip.java`, `Soy.java`: Concrete decorators dynamically wrapping any `Beverage`.
- `LowerCaseInputStream.java`: Custom I/O stream decorator.
- `KotlinChannelDelegation.kt`: Modern Kotlin `by` keyword decorator.

---

## 📝 Refactoring Tasks
1. Implement `Mocha`:
   - `getDescription()`: `beverage.getDescription() + ", Mocha"`.
   - `cost()`: `beverage.cost() + 0.20`.
2. Implement `Whip`:
   - `getDescription()`: `beverage.getDescription() + ", Whip"`.
   - `cost()`: `beverage.cost() + 0.10`.
3. Implement `Soy`:
   - `getDescription()`: `beverage.getDescription() + ", Soy"`.
   - `cost()`: `beverage.cost() + 0.15`.
4. Inspect and run `LowerCaseInputStream` and `KotlinChannelDelegation.kt`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.decorator.*"
```
