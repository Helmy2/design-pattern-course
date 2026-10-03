# Solution Explanation: Singleton Pattern & Modern Evolution

## 🔍 Key Implementation Decisions

1. **Thread-Safe Double-Checked Locking (`DatabaseConnectionPool`)**:
   - `private static volatile DatabaseConnectionPool instance`:
     The `volatile` keyword ensures that writes to the instance variable are immediately visible across all CPU caches and prevents CPU instruction reordering (where memory allocation and partial constructor initialization could leak an incomplete object to another thread).
   - The first `if (instance == null)` avoids paying the synchronization overhead once initialized.
   - The second `if (instance == null)` inside the `synchronized` block guards against race conditions when two threads simultaneously enter the first check.
   - Added reflection protection in the private constructor throwing `IllegalStateException`.

2. **Initialization-on-Demand Holder Idiom (`LazyRegistry`)**:
   - Uses Java's classloader semantics (JLS section 12.4.2). The JVM does not load or initialize `Holder` until `getInstance()` references `Holder.INSTANCE`.
   - Thread safety is guaranteed by the JVM classloader lock without any runtime synchronization penalty.

3. **Enum Singleton (`GlobalAppConfig`)**:
   - Joshua Bloch's Effective Java recommendation. Immune to reflection and deserialization attacks.

4. **Modern Kotlin `object` (`KotlinCacheRegistry`)**:
   - Replaced 25+ lines of Java creational plumbing with a concise Kotlin `object`.
   - In modern Spring Boot backend architectures, manual Singletons are replaced by Inversion of Control (IoC) `@Service` or `@Component` beans.

---

## 🧪 Verification
Run tests to verify thread safety:
```bash
./gradlew test --tests "com.course.patterns.singleton.*"
```
All assertions pass green!
