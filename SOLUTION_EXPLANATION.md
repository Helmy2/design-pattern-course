# Solution Explanation: Prototype Pattern & Modern Kotlin Evolution

## 🔍 Key Implementation Decisions

1. **Evolution from Direct Instantiation to Prototype Pattern**:
   - In the starter branch, `NetworkSecurityProfile` was a standard domain class without prototype support.
   - When creating customized profiles for different tenants, clients had to manually extract fields via getters and pass them into constructors, causing tight coupling and manual copy bugs.
   - In this solution, we introduce the **GoF Prototype Pattern**:
     - `Prototype<T>` interface establishes a polymorphic cloning contract.
     - `NetworkSecurityProfile` implements `Prototype<NetworkSecurityProfile>` with a deep copy constructor.

2. **Avoiding Java's Flawed `java.lang.Cloneable`**:
   - Java's built-in `Cloneable` interface is widely considered broken:
     - It does not declare a public `clone()` method.
     - It returns raw `Object`, requiring unchecked casting.
     - It bypasses standard constructor execution.
   - We implement a typed generic contract `Prototype<NetworkSecurityProfile>` backed by an explicit copy constructor.

3. **Memory Isolation via Deep Copy Constructor**:
   - The deep copy constructor allocates a distinct `ArrayList` for `firewallRules`:
     ```java
     this.firewallRules = new ArrayList<>(target.firewallRules);
     ```
   - This ensures complete memory isolation: mutating a clone never corrupts the original or other clones.

4. **Prototype Registry / Cache**:
   - `SecurityProfileRegistry` holds pre-configured baseline prototypes (e.g. `PCI_DSS`, `HIPAA`, `DEFAULT`).
   - Clients request fresh clones via `registry.getClone(key)` instead of repeatedly paying the cost of complex instantiation.

5. **Modern Kotlin `.copy()` Evolution**:
   - Kotlin data classes synthesize a `.copy()` method at compile-time.
   - See `KotlinSecurityProfile.kt` for idiomatic immutable cloning with targeted field adjustments.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.prototype.*"
```
All assertions pass green!
