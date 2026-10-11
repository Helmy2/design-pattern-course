package com.course.patterns.observer;

/**
 * CLEAN CODE SOLUTION:
 * Concrete Observer using the Pull model.
 */
public class ForecastDisplay implements Observer {

    private float currentPressure = 29.92f;
    private float lastPressure;
    private final WeatherData weatherData;

    public ForecastDisplay(WeatherData weatherData) {
        this.weatherData = weatherData;
        weatherData.registerObserver(this);
    }

    @Override
    public void update() {
        // Pull model: observer pulls only pressure
        this.lastPressure = this.currentPressure;
        this.currentPressure = weatherData.getPressure();
    }

    public String display() {
        if (currentPressure > lastPressure) {
            return "Forecast: Improving weather on the way!";
        } else if (currentPressure == lastPressure) {
            return "Forecast: More of the same";
        } else {
            return "Forecast: Watch out for cooler, rainy weather";
        }
    }
}
