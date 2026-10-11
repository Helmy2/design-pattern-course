# Lesson 13: Composite Pattern

> "Composes objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly."

---

## 🎯 Learning Objectives
1. **Part-Whole Hierarchies**: Model recursive tree structures containing both individual elements (Leaves) and containers (Composites).
2. **Uniformity of Client Code**: The client interacts exclusively with the abstract `Component` type, eliminating boilerplate type checks (`instanceof`) and disparate loops.
3. **Transparency vs. Safety Trade-Off**:
   - **Transparency**: Component declares child management methods (`add()`, `remove()`, `getChild()`). Leaves throw `UnsupportedOperationException`. Client treats all nodes identically.
   - **Safety**: Only Composite declares child management methods. Leaves do not have invalid methods, but client loses uniformity and must cast.
4. **Polymorphic Recursion**: Composite delegates operations down to all its children, automatically traversing arbitrary tree depth.

---

## 🏗️ The Problem: Inflexible Flat Hierarchies in Starter
Examine `Menu.java` and `Waitress.java`:
- `Menu` holds a flat `List<MenuItem>`. It can only add leaf items (`add(MenuItem)`), not sub-menus.
- Attempting to add a dessert sub-menu into the diner menu fails compilation:
  ```java
  dinerMenu.add(dessertMenu); // Compilation error! Menu only accepts MenuItem.
  ```
- The client (`Waitress`) is forced to manage a flat `List<Menu>` rather than a unified tree root.

To resolve this, we introduce the **Composite Pattern**:
- Create `MenuComponent.java` as the abstract component.
- Refactor `MenuItem` and `Menu` to extend `MenuComponent`.
- Refactor `Waitress` to hold a single root `MenuComponent`.

---

## 📝 Refactoring Tasks
1. Create `MenuComponent.java` with default `UnsupportedOperationException` methods.
2. Refactor `MenuItem.java` to extend `MenuComponent`.
3. Refactor `Menu.java` to extend `MenuComponent` and manage `List<MenuComponent>`.
4. Implement polymorphic recursion in `Menu.print(StringBuilder out)`.
5. Refactor `Waitress.java` to accept a root `MenuComponent`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.composite.*"
```
