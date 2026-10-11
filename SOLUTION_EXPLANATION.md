# Solution Explanation: Command Pattern & CQRS

## 🔍 Key Implementation Decisions

1. **Decoupling Invoker and Receiver**:
   - `RemoteControl` knows nothing about light bulbs, ceiling fan motors, or stereos. It only stores and invokes `Command` objects.
   - New appliances and commands can be wired to remote slots without changing a single line of remote control firmware.

2. **State-Tracking Undo Operations**:
   - In `CeilingFanMediumCommand`, `prevSpeed` records the fan's state immediately prior to setting medium speed.
   - When `undo()` is pushed, it evaluates `prevSpeed` and cleanly restores HIGH, MEDIUM, LOW, or OFF.

3. **The Null Object Pattern (`NoCommand`)**:
   - Initializing slots with a non-null no-op object avoids tedious defensive `if (onCommands[slot] != null)` checks before every button push.

4. **Macro Commands ("Party Mode")**:
   - `MacroCommand` batches heterogeneous commands (`LightOnCommand`, `StereoOnWithCDCommand`).
   - To roll back correctly, `undo()` unrolls the array in reverse execution order (`i = commands.length - 1; i >= 0; i--`).

5. **Enterprise CQRS & Task Queues**:
   - The Command pattern underpins thread pools (`ExecutorService.submit(Runnable task)`), message brokers, and CQRS architectures where state-mutating business requests are decoupled from query projections.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.command.*"
```
All assertions pass green!
