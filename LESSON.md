# Lesson 07: Factory Patterns (Simple Factory & Factory Method)

> "Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses."

---

## 🎯 Learning Objectives
1. **The Peril of `new`**: Instantiating concrete classes directly inside business logic binds client code to implementations, violating the **Open/Closed Principle (OCP)** and **Dependency Inversion Principle (DIP)**.
2. **Simple Factory (Programming Idiom)**: Encapsulate object creation in a dedicated factory object. Centralizes instantiation so multiple clients share it.
3. **The Factory Method (GoF Design Pattern)**: When regional franchises need customization, Simple Factory falls short. Factory Method establishes an abstract creator with a template method and lets subclasses decide which concrete products to instantiate.

---

## 🏗️ The Problem: Monolithic Coupling in `PizzaStore`
Examine `src/main/java/com/course/patterns/factorymethod/PizzaStore.java`:
- `orderPizza()` mixes **code that varies** (concrete `new GenericCheesePizza()`, `new GenericPepperoniPizza()`) with **code that stays constant** (`prepare()`, `bake()`, `cut()`, `box()`).
- Whenever a new pizza type is added or ingredients change, `PizzaStore` must be reopened and edited.
- Franchisees cannot customize styles without modifying the core store or duplicating the entire order lifecycle.

---

## 📝 The Multi-Stage Refactoring Journey
1. **Stage 1 (Next Branch: `07-pattern-simple-factory-solution`)**:
   - Extract the `new` logic out of `PizzaStore` into `SimplePizzaFactory`.
   - Compose `PizzaStore` with `SimplePizzaFactory` via constructor injection.
2. **Stage 2 (Final Branch: `07-pattern-factory-method-solution`)**:
   - Evolve `PizzaStore` into an abstract creator.
   - Defer pizza creation to regional franchise subclasses (`NYPizzaStore`, `ChicagoPizzaStore`) while keeping the `orderPizza()` preparation lifecycle immutable.

---

## 🧪 Verification
Run tests on starter:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
Switch to `07-pattern-simple-factory-solution` to see Stage 1!
