# Lesson 17: Observer Pattern & Spring Event Publisher

> "Defines a one-to-many dependency between objects so that when one object changes state, all its dependents are notified and updated automatically."

---

## 🎯 Learning Objectives
1. **Loose Coupling**: The subject knows only that observers implement a specific interface; it knows nothing about concrete observer classes.
2. **Push vs. Pull Architectures**:
   - **Push Model**: Subject pushes all data arguments to observers in `update(temp, humidity, pressure)`. Vulnerable to parameter bloat when new metrics are introduced.
   - **Pull Model**: Subject calls parameterless `update()`. Observers pull only the data they need via getters.
3. **Dynamic Subscription**: Observers can register and unregister at runtime without modifying the Subject (Open/Closed Principle).
4. **Enterprise Event-Driven Architecture — Spring `ApplicationEventPublisher`**:
   - Modern enterprise applications decouple transactional domain services from side-effects (auditing, emails, cache evictions) by publishing immutable domain events (`OrderPlacedEvent`).

---

## 🏗️ The Problem: Concrete Display Coupling in Starter
Examine `WeatherData.java`:
- Directly holds concrete `CurrentConditionsDisplay` and `ForecastDisplay` fields.
- Calling `measurementsChanged()` directly invokes their methods.
- **Flaws**:
  - Adding a new display requires editing `WeatherData` (violating OCP).
  - Displays cannot be dynamically attached or detached at runtime.

To resolve this, we introduce the **Observer Pattern**:
- Create `Subject.java` with `registerObserver`, `removeObserver`, and `notifyObservers`.
- Create `Observer.java` with a clean pull-based `update()`.
- Refactor `WeatherData` to implement `Subject` and hold `List<Observer>`.
- Refactor displays to implement `Observer` and pull readings from `WeatherData`.

---

## 📝 Refactoring Tasks
1. Create `Subject.java` interface.
2. Create `Observer.java` interface using the Pull model (`void update()`).
3. Refactor `WeatherData.java` to implement `Subject`.
4. Refactor `CurrentConditionsDisplay.java` and `ForecastDisplay.java` to implement `Observer`.
5. Implement `spring/OrderEventPublisher.java` for decoupled Spring event publishing.

---

## 🧪 Verification
Run tests:
```bash
./gradlew test --tests "com.course.patterns.observer.*"
```
