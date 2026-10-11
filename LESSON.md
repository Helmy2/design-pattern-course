# Lesson 14: Facade Pattern & Principle of Least Knowledge

> "Provides a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use."

---

## 🎯 Learning Objectives
1. **Subsystem Simplification**: Package complex interactions across disparate classes into a clean, intuitive entry point.
2. **Principle of Least Knowledge (Law of Demeter)**:
   - Talk only to your immediate friends:
     - The object itself
     - Objects passed in as method parameters
     - Any object created or instantiated inside the method
     - Any direct component of the object (`HAS-A`)
   - Avoid calling methods on objects returned by other methods (`a.getB().getC().doSomething()`).
3. **Decoupling**: Shield clients from subsystem changes and intricate configuration protocols.
4. **Non-Exclusivity**: Facades simplify common workflows without sealing off low-level access for power users who require fine-grained control.

---

## 🏗️ The Problem: Subsystem Overload in Starter
Examine `HomeTheaterClient.java`:
- To watch a movie, the client must instantiate and manually coordinate 6 different devices:
  `Amplifier`, `StreamingPlayer`, `Projector`, `Screen`, `TheaterLights`, and `Popper`.
- Violates the Principle of Least Knowledge: the client knows low-level details of every single device.
- Watching a movie requires executing a rigid 12-step sequence in exact order.

To resolve this, we introduce the **Facade Pattern**:
- Create `HomeTheaterFacade.java` that composes the 6 subsystem devices.
- Expose high-level `watchMovie(String movie)` and `endMovie()`.
- Decouple `HomeTheaterClient` from the 6 devices by injecting `HomeTheaterFacade`.

---

## 📝 Refactoring Tasks
1. Create `HomeTheaterFacade.java` wrapping the 6 subsystem devices.
2. Implement `watchMovie(String movie)` and `endMovie()` in `HomeTheaterFacade`.
3. Refactor `HomeTheaterClient.java` to depend solely on `HomeTheaterFacade`.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.facade.*"
```
