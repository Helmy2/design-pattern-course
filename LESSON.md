# Lesson 10: Prototype Pattern & Modern Kotlin Evolution

> "Specify the kinds of objects to create using a prototypical instance, and create new objects by copying or cloning this prototype."

---

## 🎯 Learning Objectives
1. **When to Use Prototypes**:
   - Heavy instantiation costs (database lookups, RPC calls).
   - Complex object graphs requiring copies with slight adjustments.
   - Decoupling client code from concrete classes.
2. **The Shallow Copy vs. Deep Copy Hazard**:
   - A shallow copy copies primitive values directly, but copies object references as-is. Both original and clone share the same inner list! Mutating the clone mutates the original.
   - A deep copy recursively clones nested collections and inner objects.
3. **Why Avoid `java.lang.Cloneable`**:
   - Flawed marker interface, returns raw `Object`, protected access, bypasses constructors.
   - Best practice: typed `Prototype<T>` interface and copy constructors.
4. **Prototype Registry**: Central registry/cache serving pre-warmed clones on demand.
5. **Modern Kotlin Evolution**: Kotlin data classes automatically synthesize `.copy(newProperty = value)`.

---

## 🏗️ The Problem: Shared Mutable State in Starter
Examine `src/main/java/com/course/patterns/prototype/`:
- `ShallowSecurityProfile.java`: Copies the list reference directly. Adding a firewall rule to a tenant profile silently adds it to the shared standard profile!
- `NetworkSecurityProfile.java`: Needs deep copy constructor and `clone()` implementation.
- `SecurityProfileRegistry.java`: Coordinates cached prototypes.

---

## 📝 Refactoring Tasks
1. In `NetworkSecurityProfile`:
   - Implement typed `Prototype<NetworkSecurityProfile>`.
   - Implement deep-copy constructor: `this.firewallRules = new ArrayList<>(target.firewallRules)`.
   - Implement `clone()` returning a new `NetworkSecurityProfile(this)`.
2. Inspect `KotlinSecurityProfile.kt` to observe how Kotlin `.copy()` provides native deep prototyping.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.prototype.*"
```
