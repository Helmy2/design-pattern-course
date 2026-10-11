# Lesson 19: Iterator Pattern & Java Streams Evolution

> "Provides a way to access the elements of an aggregate object sequentially without exposing its underlying representation."

---

## 🎯 Learning Objectives
1. **Encapsulate Aggregate Traversal**: Decouple client code from underlying data structures (arrays, lists, sets, hash maps).
2. **Single Responsibility Principle (SRP)**:
   - A collection class manages its elements.
   - An `Iterator` manages iteration state (current index, boundary checks).
3. **Uniform Client Contract**: Clients depend solely on `java.util.Iterator<T>`, enabling polymorphic traversal across disparate collection types.
4. **Modern Java & Streams Evolution**:
   - Understand the transition from explicit external iteration (`while (it.hasNext())`) to functional internal iteration via the Stream API (`stream().filter().toList()`).
   - Enterprise cursor pagination in Spring Data (`Slice<T>`, `Page<T>`).

---

## 🏗️ The Problem: Heterogeneous Collections in Starter
Examine `PancakeHouseMenu.java`, `DinerMenu.java`, and `Waitress.java`:
- Lou's Pancake House stores items in an `ArrayList<MenuItem>`.
- Mel's Diner stores items in a fixed-size `MenuItem[]` array with trailing nulls.
- `Waitress` is forced to know both concrete data structures and write two completely separate, brittle traversal loops:
  - Loop 1 for List.
  - Loop 2 for Array (with manual index and null checks).

To resolve this, we introduce the **Iterator Pattern**:
- Create `Menu.java` interface with `Iterator<MenuItem> createIterator()`.
- Create `DinerMenuIterator.java` adapting fixed-size arrays to `java.util.Iterator<MenuItem>`.
- Refactor `PancakeHouseMenu` and `DinerMenu` to implement `Menu`.
- Refactor `Waitress` to iterate uniformly over `Iterator<MenuItem>`.

---

## 📝 Refactoring Tasks
1. Create `Menu.java` interface.
2. Create `DinerMenuIterator.java` implementing `java.util.Iterator<MenuItem>`.
3. Refactor `PancakeHouseMenu.java` and `DinerMenu.java` to implement `Menu`.
4. Refactor `Waitress.java` to traverse items uniformly using iterators.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.iterator.*"
```
