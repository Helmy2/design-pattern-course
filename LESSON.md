# Lesson 15: Proxy Pattern & Demystifying Spring AOP

> "Provide a surrogate or placeholder for another object to control access to it."

---

## 🎯 Learning Objectives
1. **The Three Classic Proxy Types**:
   - **Remote Proxy**: Dispatches method calls across network addresses or RPC boundaries (RMI, gRPC stubs).
   - **Virtual Proxy**: Controls access to resource-intensive objects, deferring creation until actual invocation (lazy loading).
   - **Protection Proxy**: Controls access to real object methods based on caller permissions and access rights.
2. **Java Dynamic Proxies (`java.lang.reflect.Proxy`)**:
   - Both the Real Subject and Proxy implement the same `Subject` interface.
   - The `InvocationHandler` intercepts all method calls, enforcing security rules before delegating to the real instance.
3. **Comparing the Wrapping Patterns**:
   - **Adapter**: Converts an incompatible interface.
   - **Decorator**: Adds responsibilities dynamically without changing interface.
   - **Facade**: Simplifies access across a whole subsystem via a new interface.
   - **Proxy**: Controls access to an object using the *same* interface.
4. **Demystifying "Spring Magic" (Spring AOP)**:
   - Annotations like `@Transactional`, `@Async`, and `@Cacheable` contain no runtime code. Spring wraps beans in **Dynamic Proxies** (JDK dynamic proxy if interface exists, CGLIB/ByteBuddy subclassing if concrete class).
   - **The Self-Invocation Trap**: Calling an annotated method from within the same class invokes `this`, bypassing the proxy wrapper completely!

---

## 🏗️ The Problem: Unprotected Access & Authorization Tampering
Examine `src/main/java/com/course/patterns/proxy/problem/UnprotectedAccessProblem.java`:
- Directly exposing `PersonImpl` allows users to tamper with their own geek rating (self-rating bias).
- Non-owners can vandalize personal profile details (`setInterests`) without any access barrier.

To resolve this, we implement the **Protection Proxy Pattern**:
- `PersonProxyFactory.java`: Instantiates JDK Dynamic Proxies using `java.lang.reflect.Proxy`.
- `OwnerInvocationHandler.java`: Blocks owners from setting their own rating (`IllegalAccessException`), while permitting updates to personal interests.
- `NonOwnerInvocationHandler.java`: Permits outside users to submit ratings, while blocking unauthorized edits to personal profile details.

---

## 📝 Refactoring Tasks
1. Implement `OwnerInvocationHandler.invoke()`:
   - If method is `get*`, delegate to `method.invoke(person, args)`.
   - If method is `setGeekRating`, throw `IllegalAccessException("Owners cannot modify their own rating score!")`.
   - If method is `set*`, delegate.
2. Implement `NonOwnerInvocationHandler.invoke()`:
   - If method is `get*` or `setGeekRating`, delegate.
   - If method is `set*` (excluding rating), throw `IllegalAccessException("You cannot alter another user's personal profile!")`.
3. In `PersonProxyFactory`: Build dynamic proxies via `Proxy.newProxyInstance(...)`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.proxy.*"
```
