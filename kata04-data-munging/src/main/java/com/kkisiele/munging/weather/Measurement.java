package com.kkisiele.munging.weather;

public interface Measurement {
    int dayOfMonth();
    Temperature minTemperature();
    Temperature maxTemperature();

    default Temperature temperatureSpread() {
        return maxTemperature().subtract(minTemperature());
    }
}