# Lesson 19: Iterator Pattern & Java Streams Evolution

> "Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation."

---

## 🎯 Learning Objectives
1. **Encapsulating Traversal**: Decouple client traversal code from underlying collection types (`ArrayList`, raw arrays, hash maps, linked lists).
2. **Single Responsibility Principle (SRP)**:
   - An aggregate/collection class should have only ONE reason to change: **managing its elements** (adding, removing, storing).
   - If it also handles **navigating/traversing** the data, it violates SRP. Extracting an `Iterator` isolates that second responsibility.
3. **Array vs. List Traversal**:
   - Lou's Pancake House uses `ArrayList<MenuItem>`.
   - Mel's Diner uses a fixed-size `MenuItem[]` array.
   - By implementing a unified `Menu` interface returning `Iterator<MenuItem>`, the client (`Waitress`) iterates polymorphically with zero knowledge of arrays or lists.
4. **Modern Java Evolution**:
   - `java.lang.Iterable<T>` and the enhanced `for (MenuItem item : menu)` loop.
   - External iteration (`while(it.hasNext())`) vs. Internal iteration (`menuItems.stream().filter(...).forEach(...)`).
   - Database cursor pagination in Spring Data (`Slice<T>`, `Page<T>`).

---

## 🏗️ The Problem: Incompatible Traversal Loops & Leaking Internal Data Structures
Examine `src/main/java/com/course/patterns/iterator/problem/HeterogeneousCollectionProblem.java`:
- Mel's Diner uses an array (`MenuItem[]`), while Lou's Pancake House uses a List (`List<MenuItem>`).
- Client code (`NaiveWaitress`) is forced to write separate, incompatible loops (array indexing and bounds checks vs list loops), leaking the internal storage structures of both restaurants and violating SRP/OCP.

To resolve this, we implement the **Iterator Pattern**:
- `Menu.java`: Common interface declaring `Iterator<MenuItem> createIterator()`.
- `DinerMenuIterator.java`: Encapsulates array iteration, bounds, and null checking behind standard `java.util.Iterator`.
- `Waitress.java`: Iterates uniformly across all menus with a single polymorphic loop.

---

## 📝 Refactoring Tasks
1. Implement `DinerMenuIterator`:
   - `hasNext()`: Check whether `position < list.length && list[position] != null`.
   - `next()`: Fetch item at `position` and increment `position`.
2. Connect `DinerMenu.createIterator()` to `new DinerMenuIterator(menuItems)`.
3. In `Waitress`: Write a single unified `printMenu(Iterator<MenuItem> iterator)` helper.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.iterator.*"
```
