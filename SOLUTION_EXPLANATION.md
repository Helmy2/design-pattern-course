# Solution Explanation: Decorator Pattern & Kotlin Interface Delegation

## 🔍 Key Implementation Decisions

1. **Eliminating Class Explosion & Rigid Flags via Composition**:
   - Instead of static class combinations or rigid boolean flags on a God superclass, decorators wrap components dynamically at runtime.
   - Decorators share the same supertype (`Beverage`) as the components they wrap, allowing them to substitute transparently in client code.

2. **Recursive Runtime Delegation**:
   - When `cost()` is called on the outermost decorator (`Whip`), the call cascades:
     `Whip (+0.10) ➔ Mocha #2 (+0.20) ➔ Mocha #1 (+0.20) ➔ DarkRoast (0.99)`.
     Result: `0.99 + 0.20 + 0.20 + 0.10 = 1.49`.
   - Adheres strictly to the **Open/Closed Principle**: new condiments (Caramel, Cinnamon) or base beverages (ColdBrew) can be introduced without modifying any existing classes.

3. **Real-World Java I/O (`java.io`)**:
   - Demonstrates that `FilterInputStream` is GoF's Abstract Decorator, and `LowerCaseInputStream` extends it to decorate byte streams transparently.

4. **Modern Kotlin Interface Delegation (`by`)**:
   - In Java, writing a decorator requires writing forwarding stubs for every method on the interface.
   - In Kotlin, `class AuditedNotificationChannel(delegate: NotificationChannel) : NotificationChannel by delegate` auto-forwards all interface methods to `delegate`, leaving the developer to override only the single method they wish to augment.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.decorator.*"
```
All assertions pass green!
