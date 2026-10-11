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

## 🏗️ Architectural Evolution
1. **Stage 1 (`07-pattern-factory-method-starter`)**: Monolithic `PizzaStore` with inline `new` statements.
2. **Stage 2 (`07-pattern-simple-factory-solution`)**: Standalone `SimplePizzaFactory` composed inside `PizzaStore`.
3. **Stage 3 (`07-pattern-factory-method-solution`)**: Full GoF Factory Method framework with regional creators.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
All assertions pass green!
