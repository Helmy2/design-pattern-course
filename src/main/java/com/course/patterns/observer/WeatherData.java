package com.course.patterns.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER CODE:
 * Concrete Subject:
 * Manages observer registrations and broadcasts notifications upon state changes.
 */
public class WeatherData implements Subject {

    private final List<Observer> observers = new ArrayList<>();
    private float temperature;
    private float humidity;
    private float pressure;

    @Override
    public void registerObserver(Observer o) {
        // TODO: Step 1 - Add observer to list
        observers.add(o);
    }

    @Override
    public void removeObserver(Observer o) {
        // TODO: Step 2 - Remove observer from list
        observers.remove(o);
    }

    @Override
    public void notifyObservers() {
        // TODO: Step 3 - Iterate through observers and call observer.update()
        for (Observer observer : observers) {
            observer.update();
        }
    }

    public void setMeasurements(float temperature, float humidity, float pressure) {
        this.temperature = temperature;
        this.humidity = humidity;
        this.pressure = pressure;
        notifyObservers();
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
    public float getPressure() { return pressure; }
}
