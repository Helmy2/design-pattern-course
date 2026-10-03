# Lesson 18: Command Pattern & CQRS

> "Encapsulates a request as an object, thereby letting you parameterize clients with different requests, queue or log requests, and support undoable operations."

---

## 🎯 Learning Objectives
1. **Decoupling Invoker from Receiver**:
   - The **Invoker** (`RemoteControl`) knows only how to invoke `command.execute()` or `command.undo()`.
   - The **Receiver** (`Light`, `CeilingFan`, `Stereo`) knows how to perform the actual work (`on()`, `high()`, `setVolume()`).
   - The **Command** encapsulates the binding between action and receiver.
2. **State-Tracking Undo Operations**:
   - Commands record the receiver's state prior to mutation (`prevSpeed = ceilingFan.getSpeed()`).
   - In `undo()`, previous state is cleanly restored.
3. **The Null Object Pattern (`NoCommand`)**:
   - Initialize unassigned slots with an active no-op object to eliminate defensive `if (command != null)` checks across the codebase.
4. **Macro Commands ("Party Mode")**:
   - Batch execute an array of commands in sequence, and unroll in reverse order on undo.
5. **Real-World & Enterprise Connections**:
   - Thread pools and Job Schedulers: `Runnable` and `Callable` are classic Command interfaces.
   - **CQRS (Command Query Responsibility Segregation)**: Encapsulating business state mutations into Command objects dispatched through a central Command Bus.

---

## 🏗️ The Problem: Direct Hardware Coupling & Missing Undo
Examine `src/main/java/com/course/patterns/command/problem/TightlyCoupledRemoteProblem.java`:
- `NaiveDirectRemote` tightly binds button actions to specific vendor hardware devices (`Light`, `Stereo`).
- Supporting undo, command scheduling, or composite macros ("Party Mode") is impossible without rewriting the remote for every new appliance.

To resolve this, we implement the **Command Pattern**:
- `Command.java`: Single-method contract declaring `execute()` and `undo()`.
- `RemoteControl.java`: 7 programmable slots, `NoCommand` Null Object, and 1-touch `undoButtonWasPushed()`.
- `commands/CeilingFanMediumCommand.java`: State-tracking undo reverting to prior speed.
- `commands/MacroCommand.java`: Composite command executing sequences and unrolling in reverse order.

---

## 📝 Refactoring Tasks
1. Implement `CeilingFanMediumCommand`:
   - In `execute()`: record `prevSpeed = ceilingFan.getSpeed()`, then call `ceilingFan.medium()`.
   - In `undo()`: revert speed to `prevSpeed`.
2. Implement `MacroCommand`:
   - In `execute()`: iterate forward through commands.
   - In `undo()`: iterate in reverse from `commands.length - 1` down to `0`.
3. Verify that `RemoteControl` supports button pushes and global undo cleanly!

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.command.*"
```
