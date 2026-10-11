# Lesson 08: Abstract Factory Pattern

> "Provide an interface for creating families of related or dependent objects without specifying their concrete classes."

---

## 🎯 Learning Objectives
1. **Building on Factory Method (Lesson 07)**:
   - In Lesson 07, we created `PizzaStore`, `NYPizzaStore`, and `ChicagoPizzaStore`.
   - But notice the new architectural flaw: each store creates subclasses (`NYStyleCheesePizza`, `ChicagoStyleCheesePizza`) where **ingredients are hardcoded directly in each pizza class**.
2. **Subclass Explosion**:
   - For $N$ regional styles and $M$ pizza varieties, we are forced to maintain $N \times M$ concrete pizza subclasses (`NYStyleCheesePizza`, `NYStyleClamPizza`, `ChicagoStyleCheesePizza`, `ChicagoStyleClamPizza`, ...).
3. **The Abstract Factory Solution**:
   - Instead of regional pizza subclasses, we create a single generic `CheesePizza` and `ClamPizza`.
   - Each pizza **composes** a `PizzaIngredientFactory` (`NYPizzaIngredientFactory`, `ChicagoPizzaIngredientFactory`) to dynamically produce authentic regional ingredients.
4. **Factory Method vs. Abstract Factory**:
   - Factory Method uses **inheritance** (`PizzaStore.createPizza()`).
   - Abstract Factory uses **object composition** (`pizza.ingredientFactory.createDough()`).

---

## 🏗️ The Problem: Franchise Ingredient Inconsistency
Examine `NYPizzaStore.java` and `ChicagoPizzaStore.java`:
- Every regional pizza hardcodes its own concrete ingredients (`new ThinCrustDough()`, `new MarinaraSauce()`).
- High-level pizza classes directly depend on low-level concretes (violating DIP).
- Franchisees can swap or omit ingredients because there is no factory contract enforcing a coherent product family.

---

## 📝 Refactoring Tasks
1. **Define the Abstract Factory**:
   - Create `PizzaIngredientFactory` declaring `createDough()`, `createSauce()`, `createCheese()`, `createClams()`.
2. **Implement Regional Factories**:
   - `NYPizzaIngredientFactory`: returns authentic NY ingredients (`ThinCrustDough`, `MarinaraSauce`, `ReggianoCheese`, `FreshClams`).
   - `ChicagoPizzaIngredientFactory`: returns authentic Chicago ingredients (`ThickCrustDough`, `PlumTomatoSauce`, `MozzarellaCheese`, `FrozenClams`).
3. **Refactor Pizza Classes In-Place via Composition**:
   - Replace regional pizza subclasses with generic `CheesePizza` and `ClamPizza`.
   - Pass `PizzaIngredientFactory` into their constructors.
   - In `prepare()`, dynamically assemble ingredients from the factory.
4. **Update `NYPizzaStore` and `ChicagoPizzaStore`**:
   - Pass their respective regional factory into `new CheesePizza(ingredientFactory)`.

---

## 🧪 Verification
Run tests on starter:
```bash
./gradlew test --tests "com.course.patterns.abstractfactory.*"
```
Switch to `08-pattern-abstract-factory-solution` to review the complete Abstract Factory solution!
