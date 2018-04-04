package com.kkisiele.kata04.weather;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class FileRepositoryTest {
    private FileRepository repository;

    @Before
    public void setUp() {
        repository = new FileRepository();
    }

    @Test
    public void testGetAllMeasurements() {
        Assert.assertEquals(30, repository.getAllMeasurements().size());
        Measurement measurement = repository.getAllMeasurements().get(8);
        Assert.assertEquals(Temperature.fahrenheit(86), measurement.maxTemperature());
        Assert.assertEquals(Temperature.fahrenheit(32), measurement.minTemperature());
    }
}