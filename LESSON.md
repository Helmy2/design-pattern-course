# Lesson 18: Command Pattern & CQRS

> "Encapsulates a request as an object, thereby letting you parameterize other objects with different requests, queue or log requests, and support undoable operations."

---

## 🎯 Learning Objectives
1. **Decouple Invoker from Receiver**: The invoker (`RemoteControl`) knows only the `Command` interface; it knows nothing about the receiver (`Light`, `Stereo`, `CeilingFan`).
2. **Encapsulate Requests**: Bundle an action and a receiver into an object with a uniform `execute()` and `undo()` API.
3. **The Null Object Pattern (`NoCommand`)**: Avoid null checks before button pushes by initializing empty slots with a no-op implementation.
4. **State-Tracking Undo Operations**: Store previous state inside commands to revert accurately on `undo()`.
5. **Macro Commands**: Compose multiple commands together and unroll them in reverse order during undo.
6. **Enterprise Architecture — CQRS & Task Queues**:
   - Thread pools (`ExecutorService.submit(Runnable)`) and message consumers act as Command invokers.
   - Command Query Responsibility Segregation (CQRS) decouples mutating command handlers from read query models.

---

## 🏗️ The Problem: Hardcoded Remote Control in Starter
Examine `RemoteControl.java`:
- Directly references `Light` and `Stereo` instances.
- Button presses use hardcoded `if/else` checks for slots 0 and 1.
- **Flaws**:
  - Adding a new appliance (e.g. `CeilingFan`) requires modifying `RemoteControl` (violating OCP).
  - No capability to undo actions or execute composite macros ("Party Mode").

To resolve this, we introduce the **Command Pattern**:
- Create `Command.java` with `execute()` and `undo()`.
- Create `NoCommand.java` as the Null Object.
- Implement concrete commands (`LightOnCommand`, `CeilingFanMediumCommand`, `MacroCommand`).
- Refactor `RemoteControl` to store slots of `Command` objects and track `undoCommand`.

---

## 📝 Refactoring Tasks
1. Create `Command` interface.
2. Create `NoCommand` Null Object.
3. Implement concrete commands in `commands/ConcreteCommands.java`.
4. Refactor `RemoteControl.java` to hold command arrays with undo support.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.command.*"
```
