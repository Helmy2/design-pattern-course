package com.course.patterns.observer.problem;

import java.util.ArrayList;
import java.util.List;

/**
 * STARTER PROBLEM CODE:
 *
 * Demonstrates the flaws BEFORE introducing the Observer pattern:
 *
 * 1. Tight Coupling to Concrete Implementations:
 *    TightlyCoupledWeatherData directly instantiates and references concrete display classes.
 *
 * 2. Violation of Open/Closed Principle (OCP):
 *    Adding a new display (e.g. StatisticsDisplay, HeatIndexDisplay) forces modification
 *    of the core WeatherData class.
 *
 * 3. Inability to Subscribe / Unsubscribe Dynamically:
 *    Displays cannot be attached, detached, or swapped dynamically at runtime.
 */
public class TightlyCoupledWeatherProblem {

    public static class NaiveCurrentDisplay {
        public String update(float temp, float humidity) {
            return "Current: " + temp + "F, " + humidity + "%";
        }
    }

    public static class NaiveForecastDisplay {
        public String update(float pressure) {
            return pressure > 29.92f ? "Sunny" : "Rain";
        }
    }

    public static class RigidWeatherData {
        // Concrete coupling to specific display elements:
        private final NaiveCurrentDisplay currentDisplay = new NaiveCurrentDisplay();
        private final NaiveForecastDisplay forecastDisplay = new NaiveForecastDisplay();
        private final List<String> notificationLog = new ArrayList<>();

        public void measurementsChanged(float temp, float humidity, float pressure) {
            // Hardcoded concrete method calls:
            notificationLog.add(currentDisplay.update(temp, humidity));
            notificationLog.add(forecastDisplay.update(pressure));
        }

        public List<String> getNotificationLog() {
            return notificationLog;
        }
    }
}
