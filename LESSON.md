# Lesson 14: Facade Pattern & Principle of Least Knowledge

> "Provides a unified interface to a set of interfaces in a subsystem. Facade defines a higher-level interface that makes the subsystem easier to use."

---

## 🎯 Learning Objectives
1. **Simplifying Complex Subsystems**: Wrap complex multi-component systems (e.g., 6 home theater devices or 5 backend microservices) behind a cohesive, high-level interface.
2. **Principle of Least Knowledge / Law of Demeter**:
   - Talk only to your immediate friends.
   - Client code shouldn't need to know the configuration sequence of an amplifier, projector aspect ratio, screen motor, dimmer lights, and popcorn popper.
3. **Non-Exclusivity of Facades**:
   - Facades do not seal the subsystem into a black box. Power users can still interact directly with low-level subsystem devices when granular control is required.
4. **Pattern Comparison**:
   - **Adapter**: Converts one interface to match client expectations.
   - **Decorator**: Adds responsibilities dynamically without altering interface.
   - **Facade**: Simplifies and unifies access across an entire subsystem by exposing a higher-level contract.
5. **Enterprise Reality**:
   - Service Facades in multi-tier Spring Boot architectures (e.g. `CheckoutService`).
   - Edge API Gateways (Spring Cloud Gateway, Kong) consolidating internal microservices into simplified payloads for mobile and web clients.

---

## 🏗️ The Problem: Brittle Subsystem Orchestration in Starter
Examine `src/main/java/com/course/patterns/facade/problem/ComplexSubsystemClientProblem.java`:
- The client must directly coordinate 6 separate subsystem components through a verbose 12-method sequence.
- Violates the Principle of Least Knowledge (Law of Demeter) and couples clients tightly to subsystem internals.

To resolve this, we implement the **Facade Pattern**:
- `devices/`: Subsystem classes: `Amplifier`, `StreamingPlayer`, `Projector`, `TheaterLights`, `Screen`, `Popper`.
- `HomeTheaterFacade.java`: High-level interface providing `watchMovie(String movie)` and `endMovie()`, encapsulating the subsystem complexity while leaving subsystems accessible for advanced use.

---

## 📝 Refactoring Tasks
1. Implement `HomeTheaterFacade.watchMovie(String movie)`:
   - Turn on popper & pop popcorn.
   - Dim lights to 10%.
   - Lower screen.
   - Turn on projector & set widescreen mode.
   - Turn on amp, set player, enable surround sound, and set volume to 5.
   - Turn on player and play movie.
2. Implement `HomeTheaterFacade.endMovie()`:
   - Turn off popper, screen up, lights on, projector off, amp off, player stop and off.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.facade.*"
```
