package com.course.patterns.observer;

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
        lastPressure = currentPressure;
        currentPressure = weatherData.getPressure();
    }

    public String display() {
        if (currentPressure > lastPressure) {
            return "Improving weather on the way!";
        } else if (currentPressure == lastPressure) {
            return "More of the same";
        } else {
            return "Watch out for cooler, rainy weather";
        }
    }
}
