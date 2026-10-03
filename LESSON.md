# Lesson 05: Architecture Smells, Over-Engineering & ArchUnit

> "Clean code principles taken to dogmatic extremes often produce systems that are harder to read and maintain than the messy code they were meant to replace."

---

## 🎯 Learning Objectives
1. **Package-by-Layer vs. Package-by-Feature**:
   - Technical horizontal slicing (`controllers`, `services`, `repositories`) scatters features across the codebase and invites cyclic dependencies.
   - Package-by-feature (vertical slices: `order`, `user`) enforces high cohesion, eliminates circular compile dependencies, and establishes clear extraction boundaries.
2. **Preventing Over-Engineering (KISS & YAGNI)**:
   - Avoid **Interface Abuse**: Do not mandate an interface for every single service when only one concrete `Impl` class will ever exist.
   - Avoid **Accidental Complexity**: Don't introduce 5 layers of indirection, specifications, and strategies for a simple full-name string join.
   - Apply **The Rule of Three**: Duplicate once or twice if necessary; extract abstractions only on the third distinct occurrence.
3. **Automated Architectural Enforcement with ArchUnit**:
   - Write unit tests in Java using **ArchUnit** to verify:
     - Zero cyclic package dependencies (`slices().matching("..").should().beFreeOfCycles()`).
     - Layer isolation (controllers never call repositories directly).
     - Services reside in proper feature packages.

---

## 🏗️ The Problem: Architecture Smells in Starter
Examine `src/main/java/com/course/architecture/`:
- `packagebylayer/`: `OrderService` depends on `UserService`, and `UserService` depends on `OrderService`, causing a circular package dependency cycle!
- `overengineering/`: An absurd 5-class structure (`IUserService`, `UserIdSpecification`, `NameFormattingStrategy`, `DefaultNameFormattingStrategy`, `UserServiceImpl`) just to fetch a user and format `firstName + " " + lastName`.

---

## 📝 Refactoring Tasks
1. Break the circular dependency in `packagebylayer` by restructuring into **Package-by-Feature** or decoupling via an interface/event.
2. Refactor `OverEngineeredUserService` into a clean, pragmatic 1-class service (`UserService`) obeying KISS and YAGNI.
3. Run ArchUnit tests to verify zero cyclic dependencies and architectural compliance!

---

## 🧪 Verification
Run ArchUnit tests:
```bash
./gradlew test --tests "com.course.architecture.*"
```
