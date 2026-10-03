package com.course.patterns.observer;

import com.course.patterns.observer.spring.OrderEventPublisher;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class ObserverPatternTest {

    @Test
    @DisplayName("Verify WeatherData notifies CurrentConditionsDisplay and ForecastDisplay on sensor update")
    void testWeatherStationNotification() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        // Update 1: 80F, 65% humidity, 30.4 pressure
        weatherData.setMeasurements(80.0f, 65.0f, 30.4f);

        assertThat(currentDisplay.getTemperature()).isEqualTo(80.0f);
        assertThat(currentDisplay.getHumidity()).isEqualTo(65.0f);
        assertThat(currentDisplay.display()).contains("80.0F degrees and 65.0% humidity");
        assertThat(forecastDisplay.display()).isEqualTo("Improving weather on the way!");

        // Update 2: 82F, 70% humidity, 29.2 pressure
        weatherData.setMeasurements(82.0f, 70.0f, 29.2f);
        assertThat(currentDisplay.getTemperature()).isEqualTo(82.0f);
        assertThat(forecastDisplay.display()).isEqualTo("Watch out for cooler, rainy weather");
    }

    @Test
    @DisplayName("Problem Demonstration: Rigid WeatherData tightly couples to concrete displays without dynamic subscriptions")
    void testTightlyCoupledWeatherProblem() {
        var rigidWeather = new com.course.patterns.observer.problem.TightlyCoupledWeatherProblem.RigidWeatherData();
        rigidWeather.measurementsChanged(75.0f, 60.0f, 30.2f);

        // Flaw: tightly coupled hardcoded calls, no dynamic observers
        assertThat(rigidWeather.getNotificationLog())
                .containsExactly("Current: 75.0F, 60.0%", "Sunny");
    }

    @Test
    @DisplayName("Verify observers can unregister dynamically at runtime")
    void testObserverUnregistration() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay display = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(75.0f, 60.0f, 30.0f);
        assertThat(display.getTemperature()).isEqualTo(75.0f);

        // Unregister display
        weatherData.removeObserver(display);

        // Subsequent update does NOT affect unregistered observer
        weatherData.setMeasurements(90.0f, 40.0f, 29.8f);
        assertThat(display.getTemperature()).isEqualTo(75.0f); // Remains unchanged!
    }

    @Test
    @DisplayName("Verify Spring EventBus broadcasts OrderPlacedEvent to multiple decoupled listeners")
    void testSpringEventBus() {
        OrderEventPublisher.EventBus bus = new OrderEventPublisher.EventBus();
        List<String> auditLogs = new ArrayList<>();

        // Notification listener
        bus.register(event -> auditLogs.add("EMAIL:" + event.customerEmail()));

        // Inventory listener
        bus.register(event -> auditLogs.add("INVENTORY_RESERVE:" + event.orderId()));

        OrderEventPublisher.OrderPlacedEvent event = new OrderEventPublisher.OrderPlacedEvent(
                1001L, "dev@example.com", BigDecimal.valueOf(149.99)
        );
        bus.publish(event);

        assertThat(auditLogs).containsExactly(
                "EMAIL:dev@example.com",
                "INVENTORY_RESERVE:1001"
        );
    }
}
