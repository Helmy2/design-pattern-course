package com.course.patterns.observer;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ObserverPatternTest {

    @Test
    @DisplayName("Verify concrete displays show initial and updated values")
    void testConcreteDisplays() {
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        WeatherData weatherData = new WeatherData(currentDisplay, forecastDisplay);
        weatherData.setMeasurements(80.0f, 65.0f, 30.4f);

        assertThat(currentDisplay.display()).isEqualTo("Current conditions: 80.0F degrees and 65.0% humidity");
        assertThat(forecastDisplay.display()).isEqualTo("Forecast: Improving weather on the way!");
    }

    @Test
    @DisplayName("Problem Demonstration: WeatherData is rigidly coupled to specific displays and cannot dynamically register new observers")
    void testTightCouplingProblem() {
        CurrentConditionsDisplay currentDisplay = new CurrentConditionsDisplay();
        ForecastDisplay forecastDisplay = new ForecastDisplay();

        WeatherData weatherData = new WeatherData(currentDisplay, forecastDisplay);
        weatherData.setMeasurements(75.0f, 70.0f, 29.2f);

        // WeatherData only knows about currentDisplay and forecastDisplay.
        // It cannot attach new displays (e.g. StatisticsDisplay, HeatIndexDisplay, ThirdPartyLogger)
        // without altering the WeatherData constructor and class definition!
        assertThat(currentDisplay.display()).contains("75.0F");
        assertThat(forecastDisplay.display()).contains("cooler, rainy weather");
    }
}
