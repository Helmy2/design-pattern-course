package com.course.patterns.observer;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Observer using the Pull model.
 */
public class CurrentConditionsDisplay implements Observer {

    private float temperature;
    private float humidity;
    private final WeatherData weatherData;

    public CurrentConditionsDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        // Pull model: observer pulls only what it needs
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
    }

    public String display() {
        return "Current conditions: " + temperature + "F degrees and " + humidity + "% humidity";
    }
}
