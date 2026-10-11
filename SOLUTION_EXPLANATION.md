# Solution Explanation: Protection Proxy & JDK Dynamic Proxies

## 🔍 Key Implementation Decisions

1. **JDK Dynamic Proxies (`java.lang.reflect.Proxy`)**:
   - Built dynamically at runtime using `Proxy.newProxyInstance()`.
   - The proxy intercepts every invocation across the `Person` interface and forwards it to the dedicated `InvocationHandler` without requiring hand-coded proxy wrapper classes for every method.

2. **Access Control via InvocationHandlers**:
   - `OwnerInvocationHandler`:
     - Allows getters and profile mutations (`setName`, `setInterests`).
     - Blocks `setGeekRating` by throwing `IllegalAccessException("Owners cannot modify their own rating score!")`.
   - `NonOwnerInvocationHandler`:
     - Allows getters and external rating (`setGeekRating`).
     - Blocks profile mutations (`setName`, `setInterests`, `setGender`) by throwing `IllegalAccessException("You cannot alter another user's personal profile!")`.

3. **Exception Translation**:
   - `InvocationTargetException` thrown by reflection is unwrapped (`throw e.getCause()`) so that domain-level exceptions bubble up cleanly to the caller.

4. **Spring AOP & The Self-Invocation Trap**:
   - Spring creates dynamic proxy wrappers around `@Service` / `@Component` beans to handle transactions (`@Transactional`), caching (`@Cacheable`), and security (`@PreAuthorize`).
   - If a public method in a bean calls another method within the same bean, it calls `this.internalMethod()`, which completely bypasses the Spring proxy and its interceptor pipeline.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.proxy.*"
```
All assertions pass green!
