package com.course.patterns.observer;

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
        // Pull architecture: query only the attributes of interest
        this.temperature = weatherData.getTemperature();
        this.humidity = weatherData.getHumidity();
    }

    public String display() {
        return "Current conditions: " + temperature + "F degrees and " + humidity + "% humidity";
    }

    public float getTemperature() { return temperature; }
    public float getHumidity() { return humidity; }
}
