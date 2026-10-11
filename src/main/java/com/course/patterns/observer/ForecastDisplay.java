package com.course.patterns.observer;

public class ForecastDisplay {
    private float currentPressure = 29.92f;
    private float lastPressure;

    public void update(float pressure) {
        this.lastPressure = this.currentPressure;
        this.currentPressure = pressure;
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
