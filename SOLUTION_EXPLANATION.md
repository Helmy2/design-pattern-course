# Solution Explanation: Observer Pattern & Spring Event Publisher

## 🔍 Key Implementation Decisions

1. **Loose Coupling**:
   - `WeatherData` (the Subject) does not import or know about `CurrentConditionsDisplay` or `ForecastDisplay`.
   - It only tracks `List<Observer>`. New displays or logging observers can register and unregister at runtime without modifying the Subject.

2. **Push vs. Pull Architecture**:
   - In the classic Push model, `update(float temp, float humidity, float pressure)` forced all observers to accept three arguments even if a display only needed humidity. Adding a fourth sensor reading would break all existing observer implementations across the codebase.
   - In the Pull model implemented here:
     - `Observer.update()` takes zero arguments.
     - Observers pull only the metrics they require via getters (`weatherData.getTemperature()`).

3. **Enterprise Spring Boot Event Bus (`ApplicationEventPublisher`)**:
   - In enterprise Spring Boot applications, in-memory domain events are represented as immutable records (e.g. `OrderPlacedEvent`).
   - The publishing service calls `publisher.publishEvent(event)`.
   - Independent listeners annotate methods with `@EventListener` and `@Async` to handle notifications, cache invalidation, and auditing in total isolation from the primary transactional thread.

---

## 🧪 Verification
Run tests to verify the solution:
```bash
./gradlew test --tests "com.course.patterns.observer.*"
```
All assertions pass green!
