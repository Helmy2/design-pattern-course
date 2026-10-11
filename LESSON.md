# Lesson 12: Decorator Pattern & Kotlin Interface Delegation

> "Attaches additional responsibilities to an object dynamically. Decorators provide a flexible alternative to subclassing for extending functionality."

---

## 🎯 Learning Objectives
1. **The Open/Closed Principle (OCP)**: Classes should be open for extension, but closed for modification.
2. **Composition over Inheritance**: Instead of exploding classes or overloading God classes with boolean flags, wrap components dynamically at runtime.
3. **GoF Decorator Structure**:
   - `Component`: Abstract supertype (`Beverage`) shared by concrete components and decorators.
   - `ConcreteComponent`: The base object being wrapped (`DarkRoast`, `Espresso`).
   - `Decorator`: Abstract decorator (`CondimentDecorator`) that `IS-A` `Beverage` and `HAS-A` `Beverage`.
   - `ConcreteDecorator`: Wraps a component and augments behavior (`Mocha`, `Soy`, `Whip`).
4. **Real-World Java I/O**: `java.io.FilterInputStream` as GoF's Abstract Decorator (`LowerCaseInputStream`).
5. **Modern Kotlin Interface Delegation**: Class delegation using the `by` keyword.

---

## 🏗️ The Problem: The Boolean Flags Smell in Starter
Examine `Beverage.java`:
- The base `Beverage` class tracks condiments via boolean flags (`hasMocha()`, `hasWhip()`, `hasSoy()`), and calculates total condiment cost in `cost()`.
- **Flaws**:
  - Price changes for condiments force edits directly into `Beverage` (violating OCP).
  - New condiments (Caramel, Cinnamon) require altering `Beverage` and adding new methods.
  - Customers cannot order a "Double Mocha" (boolean flag cannot track quantity).
  - Tea beverages inherit irrelevant flags like steamed milk.

To resolve this, we refactor `Beverage` to pure abstract methods and introduce the **Decorator Pattern**.

---

## 📝 Refactoring Tasks
1. Refactor `Beverage.java` to an abstract base class with `getDescription()` and `abstract double cost()`.
2. Refactor `DarkRoast.java` and `Espresso.java` to return pure base costs without calling `super.cost()`.
3. Create `CondimentDecorator.java` extending `Beverage`.
4. Create `Mocha.java`, `Soy.java`, and `Whip.java` extending `CondimentDecorator`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.decorator.*"
```
