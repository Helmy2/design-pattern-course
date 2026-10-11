# Lesson 15: Proxy Pattern & Spring AOP Self-Invocation Trap

> "Provides a surrogate or placeholder for another object to control access to it."

---

## 🎯 Learning Objectives
1. **Protection Proxy**: Control access to methods based on caller authorization and business rules.
2. **JDK Dynamic Proxies (`java.lang.reflect.Proxy`)**:
   - Create proxy instances dynamically at runtime without manually writing forwarding wrappers for every method.
   - Implement `InvocationHandler` to intercept method calls.
3. **The Proxy Pattern Family**:
   - **Protection Proxy**: Access control (demonstrated with dating profile permissions).
   - **Remote Proxy**: RMI / RPC stub for remote network objects.
   - **Virtual Proxy**: Lazy loading of expensive resources (e.g. high-resolution images).
   - **Caching Proxy**: Transparently cache return values.
4. **Spring AOP & The Self-Invocation Trap**:
   - Understand why `@Transactional` and `@Cacheable` fail when method A calls method B in the same class (bypassing the proxy wrapper).

---

## 🏗️ The Problem: Unprotected Domain Objects in Starter
Examine `PersonImpl.java`:
- Any client with a reference to a `Person` can invoke any method without restriction.
- **Flaws**:
  - A user can artificially inflate their own rating score via `setGeekRating(10)`.
  - Outside users can overwrite another person's personal interests and profile details via `setInterests(...)`.

To resolve this, we introduce **Protection Proxies** via Java's dynamic proxy mechanism:
- `OwnerInvocationHandler`: Allows getters and profile mutations (`setName`, `setInterests`), but blocks `setGeekRating` by throwing `IllegalAccessException`.
- `NonOwnerInvocationHandler`: Allows getters and rating (`setGeekRating`), but blocks profile mutations (`set*`).
- `PersonProxyFactory`: Creates dynamic proxies using `Proxy.newProxyInstance()`.

---

## 📝 Refactoring Tasks
1. Create `OwnerInvocationHandler` implementing `InvocationHandler`.
2. Create `NonOwnerInvocationHandler` implementing `InvocationHandler`.
3. Create `PersonProxyFactory` creating dynamic proxies for owners and non-owners.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.proxy.*"
```
