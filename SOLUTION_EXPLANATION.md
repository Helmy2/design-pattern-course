# Solution Explanation: Facade Pattern & Principle of Least Knowledge

## 🔍 Key Implementation Decisions

1. **Subsystem Simplification**:
   - `HomeTheaterFacade` aggregates `Amplifier`, `StreamingPlayer`, `Projector`, `Screen`, `TheaterLights`, and `Popper`.
   - Replaced a complex 12-step sequence with clean, unified entry points:
     - `watchMovie("Interstellar")`
     - `endMovie()`

2. **Principle of Least Knowledge / Law of Demeter**:
   - The client (`HomeTheaterClient`) only talks to its direct friend: `HomeTheaterFacade`.
   - The client has no need to know the specific audio configuration channels of the amplifier or the aspect ratio string of the projector.
   - Reduced client coupling from 6 external subsystem classes down to 1 facade.

3. **Non-Exclusivity**:
   - The facade simplifies the typical use cases without sealing away the underlying classes. A power-user client can still directly access `projector.wideScreenMode()` or `amp.setVolume(11)`.

4. **Enterprise Applications**:
   - In Spring Boot backends, `CheckoutService` acts as a Service Facade orchestrating `CartService`, `PaymentGateway`, `InventoryClient`, `TaxEngine`, and `NotificationService`.
   - In microservices, an API Gateway acts as an architectural facade translating single client HTTP requests into parallel gRPC calls across internal domain services.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.facade.*"
```
All assertions pass green!
