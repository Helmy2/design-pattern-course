# Lesson 07: Factory Method Pattern

> "Define an interface for creating an object, but let subclasses decide which class to instantiate. Factory Method lets a class defer instantiation to subclasses."

---

## 🎯 Learning Objectives
1. **The Peril of `new`**: Instantiating concrete classes directly binds client code to implementations, violating the Open/Closed Principle.
2. **Simple Factory (Idiom)**: Encapsulates instantiation in a standalone helper class. Good for basic consolidation, but lacks architectural flexibility for regional variation.
3. **The Factory Method Framework**:
   - **Creator Hierarchy**: `PizzaStore` (abstract creator) declares `protected abstract Pizza createPizza(String type)` and coordinates the standard procedure `orderPizza()`. Subclasses (`NYPizzaStore`, `ChicagoPizzaStore`) implement `createPizza()`.
   - **Product Hierarchy**: `Pizza` (abstract product) and concrete products (`NYStyleCheesePizza`, `ChicagoStyleCheesePizza`).
4. **Parallel Class Hierarchies**: Understand how creators and products evolve in lockstep without tight coupling.

---

## 🏗️ The Problem: Franchise Inconsistency
Examine `src/main/java/com/course/patterns/factorymethod/`:
- `NaivePizzaStore.java`: `orderPizza()` hardcodes concrete `new` instantiations for every pizza type. Adding regional franchises with custom styles would duplicate the preparation procedure (`prepare()`, `bake()`, `cut()`, `box()`) or break consistency.
- `PizzaStore.java`: Abstract creator needs to be implemented with concrete regional creators `NYPizzaStore` and `ChicagoPizzaStore`.

---

## 📝 Refactoring Tasks
1. Inspect `SimplePizzaFactory` as a warm-up.
2. Implement `NYPizzaStore` and `ChicagoPizzaStore` extending abstract `PizzaStore`:
   - Override `protected Pizza createPizza(String item)`
   - In `NYPizzaStore`: Return `NYStyleCheesePizza` (thin crust, marinara, diagonal slices).
   - In `ChicagoPizzaStore`: Return `ChicagoStyleCheesePizza` (extra thick deep dish crust, plum tomato sauce, square slices).
3. Verify that `pizzaStore.orderPizza("cheese")` runs the complete preparation lifecycle decoupled from concrete pizza types!

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
