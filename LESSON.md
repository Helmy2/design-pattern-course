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
- `NaivePizzaStore.java`: `orderPizza()` hardcodes concrete `new` instantiations for every pizza type. Adding regional franchises with custom styles would either duplicate the preparation procedure (`prepare()`, `bake()`, `cut()`, `box()`) or require modifying `NaivePizzaStore` with messy nested conditionals.
- `SimplePizzaFactory.java`: Encapsulates object creation into a helper class, but fails when regional stores want to take charge of their own style while retaining the franchisor's standardized order-and-bake process.

---

## 📝 Refactoring Tasks (Applying Factory Method)
1. **Define the Creator Hierarchy**:
   - Create an abstract class `PizzaStore`:
     - Implement public template method `orderPizza(String type)`: calls `createPizza(type)`, validates non-null, then runs `prepare()`, `bake()`, `cut()`, `box()`, and returns the pizza.
     - Declare protected abstract factory method `createPizza(String type)`.
2. **Implement Concrete Creators**:
   - Create `NYPizzaStore extends PizzaStore`: overrides `createPizza` to instantiate NY-style pizzas.
   - Create `ChicagoPizzaStore extends PizzaStore`: overrides `createPizza` to instantiate Chicago-style deep-dish pizzas.
3. **Define Regional Product Hierarchies**:
   - Extend `Pizza` for regional varieties:
     - `NYStyleCheesePizza`: thin crust, marinara, grated reggiano cheese, diagonal cut.
     - `ChicagoStyleCheesePizza`: extra thick crust, plum tomato sauce, mozzarella cheese, square cut override.
4. Verify that calling `nyStore.orderPizza("cheese")` and `chicagoStore.orderPizza("cheese")` executes the standardized preparation lifecycle decoupled from concrete pizza types!

---

## 🧪 Verification
Run tests on starter:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
Switch to `07-pattern-factory-method-solution` to review the complete Factory Method architecture and test suite.
