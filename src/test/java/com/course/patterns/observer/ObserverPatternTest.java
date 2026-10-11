package com.course.patterns.observer;

import com.course.patterns.observer.spring.OrderEventPublisher.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObserverPatternTest {

    @Test
    @DisplayName("Verify WeatherData notifies subscribed displays via Pull model")
    void testObserverPullModelNotification() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);
        ForecastDisplay forecastDisplay = new ForecastDisplay(weatherData);

        weatherData.setMeasurements(80.0f, 65.0f, 30.4f);

        assertThat(currentDisplay.display()).isEqualTo("Current conditions: 80.0F degrees and 65.0% humidity");
        assertThat(forecastDisplay.display()).isEqualTo("Forecast: Improving weather on the way!");
    }

    @Test
    @DisplayName("Verify observers can unregister at runtime without breaking the Subject")
    void testDynamicDeregistration() {
        WeatherData weatherData = new WeatherData();
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay(weatherData);

        weatherData.setMeasurements(80.0f, 65.0f, 30.4f);
        assertThat(currentDisplay.display()).contains("80.0F");

        // Unregister
        weatherData.removeObserver(currentDisplay);

        // Update again: currentDisplay should NOT receive new measurements
        weatherData.setMeasurements(95.0f, 40.0f, 29.8f);
        assertThat(currentDisplay.display()).contains("80.0F"); // Remains unchanged
    }

    @Test
    @DisplayName("Verify Enterprise Spring Event Publisher broadcasts events to decoupled listeners")
    void testSpringEventPublisher() {
        DomainEventPublisher publisher = new DomainEventPublisher();
        AuditLogListener auditLog = new AuditLogListener();
        EmailNotificationListener emailListener = new EmailNotificationListener();

        publisher.registerListener(auditLog);
        publisher.registerListener(emailListener);

        OrderPlacedEvent event = new OrderPlacedEvent("ORD-999", "dev@course.com", 149.99);
        publisher.publishEvent(event);

        assertThat(auditLog.getLogs())
                .containsExactly("AUDIT: Order ORD-999 placed for $149.99");
        assertThat(emailListener.getSentEmails())
                .containsExactly("EMAIL to dev@course.com: Confirmation for ORD-999");
    }
}
