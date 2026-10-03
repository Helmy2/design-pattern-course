# Lesson 06: Singleton Pattern & Modern JVM Evolution

> "Ensure a class has only one instance and provide a global point of access to it."

---

## 🎯 Learning Objectives
1. **The Four Eras of Java Singletons**:
   - **Era 1 (Unsafe Lazy)**: Race condition hazard under concurrent thread access.
   - **Era 2 (Double-Checked Locking)**: `volatile` keyword to avoid memory instruction reordering, double-check locking around `synchronized`, and constructor reflection defense.
   - **Era 3 (Initialization-on-Demand Holder Idiom)**: Leverages JVM classloader guarantees for lazy, thread-safe initialization without lock contention.
   - **Era 4 (Enum Singleton)**: Effective Java recommendation; inherently serialization-safe and reflection-proof.
2. **Modern JVM & Framework Evolution**:
   - **Kotlin `object`**: First-class language keyword providing thread-safe, lazy classloader initialization in a single line.
   - **Spring Boot IoC**: Container-managed singletons (`@Service`, `@Component`) that avoid global static state and enable constructor injection for easy mocking.
3. **The Anti-Pattern Critique**:
   - Hidden dependencies, unit testing nightmare (shared mutable state leaking across test runs), and SRP violation.

---

## 🏗️ The Problem: Concurrency Hazards in Starter
Examine `src/main/java/com/course/patterns/singleton/`:
- `UnsafeConnectionPool.java`: Does not synchronize `getInstance()`. When 100 threads invoke `getInstance()` simultaneously via `CountDownLatch`, multiple distinct instances are created in memory!
- `DatabaseConnectionPool.java`: Unimplemented Double-Checked Lock.
- `LazyRegistry.java`: Unimplemented Holder class.

---

## 📝 Refactoring Tasks
1. Implement thread-safe Double-Checked Locking in `DatabaseConnectionPool`:
   - Mark `instance` as `volatile`.
   - Double check `instance == null` inside `synchronized(DatabaseConnectionPool.class)`.
   - Guard constructor against reflection instantiation.
2. Implement Initialization-on-demand Holder idiom in `LazyRegistry`.
3. Implement `GlobalAppConfig` as an `enum`.
4. Inspect and run `KotlinCacheRegistry` to see how modern Kotlin accomplishes this in 1 line.

---

## 🧪 Verification
Run concurrency tests:
```bash
./gradlew test --tests "com.course.patterns.singleton.*"
```
