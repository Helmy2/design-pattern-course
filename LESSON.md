# Lesson 17: Observer Pattern & Spring ApplicationEventPublisher

> "Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."

---

## 🎯 Learning Objectives
1. **Loose Coupling**: The `Subject` knows only that an observer implements the `Observer` interface. Observers can be added, swapped, or removed dynamically at runtime without modifying the Subject.
2. **Push vs. Pull Architecture**:
   - **Push Model**: Subject pushes state as method arguments (`update(temp, humidity, pressure)`). Adding a sensor reading breaks all existing observer signatures.
   - **Pull Model (Recommended)**: Subject notifies observers via parameterless `update()`, and observers call getters on the Subject to pull only what they care about.
3. **Enterprise Reality — Spring Boot Event Bus**:
   - Instead of manual `Subject` registries, Spring applications use `ApplicationEventPublisher`.
   - Domain events are declared as immutable records (`OrderPlacedEvent`), published via `publisher.publishEvent(event)`, and handled asynchronously or synchronously via `@EventListener`.

---

## 🏗️ The Problem: Hardcoded Concrete Displays & OCP Violations
Examine `src/main/java/com/course/patterns/observer/problem/TightlyCoupledWeatherProblem.java`:
- `RigidWeatherData` maintains hardcoded references to specific display classes (`NaiveCurrentDisplay`, `NaiveForecastDisplay`).
- Adding, removing, or swapping displays requires modifying `measurementsChanged()` directly, violating the Open/Closed Principle.
- Dynamic runtime subscription/unsubscription is impossible without an abstraction.

To resolve this, we implement the **Observer Pattern**:
- `Subject.java` & `Observer.java`: Contracts for loose coupling.
- `WeatherData.java`: Manages dynamic list of `Observer` subscribers and broadcasts changes.
- `CurrentConditionsDisplay.java` / `ForecastDisplay.java`: Decoupled observers registering dynamically.
- `spring/OrderEventPublisher.java`: Decoupled domain event broadcasting via Spring-style EventBus.

---

## 📝 Refactoring Tasks
1. Implement `WeatherData`:
   - `registerObserver(Observer o)`
   - `removeObserver(Observer o)`
   - `notifyObservers()`: Notify all registered observers on state change.
2. Implement `CurrentConditionsDisplay`:
   - Pull model: query `weatherData.getTemperature()` and `weatherData.getHumidity()`.
3. In `OrderEventPublisher`:
   - Publish `OrderPlacedEvent` and verify that listeners receive the event decoupled from the order service.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.observer.*"
```
