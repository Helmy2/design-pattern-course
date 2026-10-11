# Lesson 07A: Simple Factory (The Programming Idiom)

> "Encapsulate what varies. If object creation varies across changes, move instantiation into an object whose only job is to create products."

---

## 🎯 Learning Objectives
1. **The Role of Simple Factory**: Centralize instantiation into a dedicated object (`SimplePizzaFactory`).
2. **Decoupling Client Logic**: `PizzaStore` depends on the factory rather than directly invoking `new` on concrete pizza classes.
3. **Recognizing the Limit**: Simple Factory solves code duplication, but cannot support regional franchises while enforcing franchisor standards.

---

## 🏗️ Refactoring Summary (Starter ➔ Simple Factory)
- `SimplePizzaFactory.java`: Contains the `createPizza(String type)` factory method.
- `PizzaStore.java`: Injected with `SimplePizzaFactory` in its constructor. `orderPizza()` delegates to `factory.createPizza(type)`.

---

## 🚀 The Next Challenge: Regional Franchising
In the next stage (`07-pattern-factory-method-solution`), we need regional franchises:
- `NYPizzaStore`: makes New York style pizzas (thin crust, marinara, diagonal slices).
- `ChicagoPizzaStore`: makes Chicago style deep-dish pizzas (thick crust, plum tomato sauce, square slices).
- The corporate franchisor must guarantee that every store strictly follows the `orderPizza()` template (`prepare()`, `bake()`, `cut()`, `box()`), without rogue deviations.

---

## 🧪 Verification
Run tests on this branch:
```bash
./gradlew test --tests "com.course.patterns.factorymethod.*"
```
Switch to `07-pattern-factory-method-solution` to see how GoF Factory Method solves the regional franchise challenge!
