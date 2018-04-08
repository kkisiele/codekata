package com.kkisiele.kata04.weather;

import java.util.Comparator;
import java.util.List;

public class WeatherFacade {
    private final Repository repository;

    public WeatherFacade() {
        repository = new TextFileRepository();
    }

    public int findDayWithSmallestTemperatureSpread() {
        List<Measurement> measurements = repository.getAllMeasurements();
        measurements.sort(Comparator.comparing(Measurement::temperatureSpread));
        return measurements.get(0).dayOfMonth();
    }
}