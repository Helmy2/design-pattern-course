# Solution Explanation: Factory Method Pattern

## 🔍 Key Implementation Decisions

1. **Evolution Across Stages**:
   - **Stage 1 (Starter)**: Monolithic `PizzaStore` tightly coupled via `new GenericCheesePizza()`, violating OCP.
   - **Stage 2 (Simple Factory Idiom)**: Extracted creation into standalone `SimplePizzaFactory` (`HAS-A` composition). Good for basic reuse, but unable to enforce corporate lifecycle rules across regional franchises.
   - **Stage 3 (Factory Method Pattern)**: Turned `PizzaStore` into an abstract creator. The `orderPizza()` template method enforces the corporate preparation procedure (`prepare() ➔ bake() ➔ cut() ➔ box()`), while deferring concrete instantiation to regional subclasses (`NYPizzaStore`, `ChicagoPizzaStore`).

2. **Parallel Class Hierarchies**:
   - **Creator Hierarchy**: `PizzaStore` (abstract) ➔ `NYPizzaStore`, `ChicagoPizzaStore` (concrete).
   - **Product Hierarchy**: `Pizza` (abstract) ➔ `NYStyleCheesePizza`, `ChicagoStyleCheesePizza` (concrete).
   - Both hierarchies evolve in lockstep without compile-time coupling between `PizzaStore` and concrete pizza classes.

3. **Strict Adherence to SOLID Principles**:
   - **Open/Closed Principle (OCP)**: Adding a new franchise (e.g. `CaliforniaPizzaStore`) requires zero modifications to `PizzaStore`.
   - **Dependency Inversion Principle (DIP)**: `PizzaStore` depends exclusively on the abstract `Pizza` product, not concrete pizzas.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
All assertions pass green!
