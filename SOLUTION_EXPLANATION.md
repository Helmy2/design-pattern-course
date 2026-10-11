# Solution Explanation: Abstract Factory Pattern

## 🔍 Key Implementation Decisions

1. **Evolution from Factory Method (Lesson 07) to Abstract Factory (Lesson 08)**:
   - In Lesson 07 (Factory Method), `NYPizzaStore` and `ChicagoPizzaStore` each created their own regional pizza subclasses (`NYStyleCheesePizza`, `ChicagoStyleCheesePizza`).
   - The problem was **subclass explosion** and **lack of ingredient consistency**: each pizza subclass hardcoded its own ingredients via `new` inside `prepare()`.
   - In this solution, we refactored pizzas to use **composition** with `PizzaIngredientFactory`.

2. **The Abstract Factory Interface (`PizzaIngredientFactory`)**:
   - Declares factory methods for each member of the ingredient family: `createDough()`, `createSauce()`, `createCheese()`, `createClams()`.
   - Concrete factories (`NYPizzaIngredientFactory`, `ChicagoPizzaIngredientFactory`) guarantee that all components within a region belong to the same family, preventing rogue substitutions.

3. **Elimination of Regional Pizza Subclasses**:
   - Instead of $N \times M$ subclasses (`NYStyleCheesePizza`, `ChicagoStyleCheesePizza`, etc.), we only need generic products: `CheesePizza` and `ClamPizza`.
   - Both receive a `PizzaIngredientFactory` and dynamically assemble ingredients during `prepare()`.

4. **Comparison Matrix**:
   - **Factory Method**: Uses **inheritance** (`PizzaStore.createPizza()`) to produce a single product.
   - **Abstract Factory**: Uses **composition** (`pizza.ingredientFactory.createDough()`) to produce an entire family of related products.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.abstractfactory.*"
```
All assertions pass green!
