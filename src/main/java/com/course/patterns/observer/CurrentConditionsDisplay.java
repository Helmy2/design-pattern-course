package com.course.patterns.observer;

public class CurrentConditionsDisplay {
    private float temperature;
    private float humidity;

    public void update(float temperature, float humidity) {
        this.temperature = temperature;
        this.humidity = humidity;
    }

    public String display() {
        return "Current conditions: " + temperature + "F degrees and " + humidity + "% humidity";
    }
}
