package com.kkisiele.kata04.weather;

public interface Measurement {
    int dayOfMonth();
    Temperature minTemperature();
    Temperature maxTemperature();

    default Temperature temperatureSpread() {
        return maxTemperature().subtract(minTemperature());
    }
}