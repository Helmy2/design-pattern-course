package com.course.patterns.observer;

/**
 * STARTER ANTI-PATTERN: Tightly Coupled Weather Publisher
 *
 * Flaws:
 * 1. Tight Coupling to Concrete Classes: Directly references specific display instances.
 * 2. Violates Open/Closed Principle: Adding a new display requires modifying WeatherData.
 * 3. Cannot Dynamically Subscribe/Unsubscribe: Displays cannot be attached or detached at runtime.
 */
public class WeatherData {
    private float temperature;
    private float humidity;
    private float pressure;

    private final CurrentConditionsDisplay currentDisplay;
    private final ForecastDisplay forecastDisplay;

    public WeatherData(CurrentConditionsDisplay currentDisplay, ForecastDisplay forecastDisplay) {
        this.currentDisplay = currentDisplay;
        this.forecastDisplay = forecastDisplay;
    }

    public void measurementsChanged() {
        currentDisplay.update(temperature, humidity);
        forecastDisplay.update(pressure);
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        measurementsChanged();
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
    public float getPressure() { return pressure; }
}
