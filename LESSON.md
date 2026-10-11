# Lesson 10: Prototype Pattern & Modern Kotlin Evolution

> "Specify the kinds of objects to create using a prototypical instance, and create new objects by copying or cloning this prototype."

---

## 🎯 Learning Objectives
1. **When to Use Prototypes**:
   - Heavy instantiation costs (expensive database queries, network configuration lookups).
   - Creating copies of complex objects without coupling client code to concrete classes.
   - Enabling clients to clone an object even when holding only an interface reference.
2. **The Shallow Copy vs. Deep Copy Hazard**:
   - A shallow copy copies primitive values directly, but copies object references as-is. Both original and clone share the same inner list! Mutating the clone mutates the original.
   - A deep copy recursively clones nested collections and inner objects.
3. **Why Avoid `java.lang.Cloneable`**:
   - Flawed marker interface, returns raw `Object`, protected access, bypasses constructors.
   - Best practice: typed `Prototype<T>` interface and copy constructors.
4. **Prototype Registry**: Central registry/cache serving pre-warmed clones on demand.
5. **Modern Kotlin Evolution**: Kotlin data classes automatically synthesize `.copy(newProperty = value)` for immutable prototyping.

---

## 🏗️ The Problem: Creating Duplicates Without Prototype
Examine `NetworkSecurityProfile.java`:
- Creating a security profile involves setting up complex firewall rule lists and bandwidth policies.
- When new tenants or clients need a slightly customized version of a baseline profile (e.g. `PCI_DSS` or `DEFAULT`), they must manually extract fields via getters and pass them to a new `NetworkSecurityProfile(...)` constructor.
- This breaks encapsulation, couples client code to concrete classes, and risks shallow copying bugs.

---

## 📝 Refactoring Tasks (Applying Prototype Pattern)
1. **Define the Prototype Contract**:
   - Create generic interface `Prototype<T>` declaring `T clone()`.
2. **Implement Prototype In-Place on `NetworkSecurityProfile`**:
   - Implement `Prototype<NetworkSecurityProfile>`.
   - Add a private/protected **deep copy constructor**:
     ```java
     public NetworkSecurityProfile(NetworkSecurityProfile target) {
         if (target != null) {
             this.profileName = target.profileName;
             this.maxBandwidthMbps = target.maxBandwidthMbps;
             this.firewallRules = new ArrayList<>(target.firewallRules); // Deep copy!
         }
     }
     ```
   - Implement `clone()` returning `new NetworkSecurityProfile(this)`.
3. **Implement Prototype Registry / Cache**:
   - Create `SecurityProfileRegistry` storing pre-warmed baseline prototypes in a `Map<String, NetworkSecurityProfile>`.
   - Provide `getClone(String key)` returning fresh, isolated clones.
4. **Inspect Kotlin Alternative**:
   - Check `KotlinSecurityProfile.kt` to observe how Kotlin `.copy()` achieves clean immutable cloning natively.

---

## 🧪 Verification
Run tests on starter:
```bash
./gradlew test --tests "com.course.patterns.prototype.*"
```
Switch to `10-pattern-prototype-solution` to review the refactored Prototype solution!
