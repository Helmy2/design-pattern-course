# Solution Explanation: Simple Factory (Programming Idiom)

## 🔍 Key Implementation Decisions

1. **Encapsulating Object Creation**:
   - `PizzaStore` no longer calls `new GenericCheesePizza()` or `new GenericPepperoniPizza()`.
   - Instead, all concrete instantiation is delegated to `SimplePizzaFactory.createPizza(type)`.
   - Multiple clients (e.g. `PizzaStore`, `OnlinePizzaDeliveryService`, `MobileAppMenu`) can now reuse this single factory without duplicating `if/else` creation logic.

2. **Composition Over Hardcoded Coupling**:
   - `PizzaStore` composes `SimplePizzaFactory` via constructor dependency injection (`HAS-A`).
   - `orderPizza()` focuses strictly on the invariant domain lifecycle: `prepare() ➔ bake() ➔ cut() ➔ box()`.

3. **Why Simple Factory is NOT a GoF Design Pattern**:
   - Simple Factory is a programmatic idiom, not an architectural pattern.
   - It centralizes creation, but does not provide dynamic architectural flexibility or parallel class hierarchies.

---

## ⚠️ The Limitation: The Franchise Expansion Problem
What happens when the business expands and opens franchises in **New York** and **Chicago**?
- New York customers expect thin crust with marinara sauce and diagonal cuts.
- Chicago customers expect deep dish crust with plum tomato sauce and square cuts.

If we keep using Simple Factory:
- We could create `NYPizzaFactory` and `ChicagoPizzaFactory`, passing them to `PizzaStore`.
- **The Pitfall**: The franchisor loses control over the store's preparation procedure. A regional store could bypass the factory or alter baking/cutting standards.
- To bind the store to the creation while allowing subclasses to decide *what* to instantiate, we need the **Factory Method Pattern**!

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
All assertions pass green!
