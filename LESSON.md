# Lesson 13: Composite Pattern

> "Compose objects into tree structures to represent part-whole hierarchies. Composite lets clients treat individual objects and compositions of objects uniformly."

---

## 🎯 Learning Objectives
1. **Part-Whole Hierarchies**: Model recursive nested structures (like a Dessert sub-menu nested inside a Dinner menu, or directories holding subdirectories and files).
2. **Uniform Treatment**: Client code does not branch with `if (item instanceof SubMenu)` or maintain separate loops. It calls `print()` on any `MenuComponent` uniformly.
3. **Polymorphic Recursion**: In container nodes (`Menu`), operations iterate over children and delegate polymorphically down the tree branch.
4. **Transparency vs. Safety Trade-Off**:
   - **Transparency (chosen here)**: Placing child-management methods (`add`, `remove`, `getChild`) in the abstract base class `MenuComponent` makes leaves and composites interchangeable to clients, but leaves throw `UnsupportedOperationException` if called.
   - **Safety**: Defining child management exclusively in `Menu` avoids runtime errors on leaves, but loses uniformity and forces clients to typecast.

---

## 🏗️ The Problem: Inflexible Tree Hierarchies in Starter
Examine `src/main/java/com/course/patterns/composite/problem/InflexibleMenuHierarchyProblem.java`:
- Individual items (`FlatMenuItem`) and sub-menus (`NestedSubMenu`) have incompatible types.
- Client printers are burdened with separate loops and cannot handle arbitrary nesting depth (e.g., dessert sub-menus inside diner menus).

To resolve this, we implement the **Composite Pattern**:
- `MenuComponent.java`: Unified abstract base class for both leaves and composites.
- `MenuItem.java`: Leaf node representing individual dishes.
- `Menu.java`: Composite node containing `List<MenuComponent>`, delegating `print()` recursively.
- `Waitress.java`: Client that triggers menu printing with a single polymorphic call.

---

## 📝 Refactoring Tasks
1. Implement `Menu`:
   - Store child components in `List<MenuComponent>`.
   - Implement `add(MenuComponent)` and `remove(MenuComponent)`.
   - Implement `print()`: Print menu name & description, then loop through all child components and call `child.print()` recursively!
2. In `Waitress`: Verify uniform traversal across root menus, sub-menus, and leaf items.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.composite.*"
```
