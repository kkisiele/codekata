package com.kkisiele.datamunging.weather;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WeatherFacadeTest {
    private WeatherFacade facade;

    @Before
    public void setUp() {
        facade = new WeatherFacade();
    }

    @Test
    public void testFindDayWithSmallestTemperatureSpread() {
        Assert.assertEquals(14, facade.findDayWithSmallestTemperatureSpread());
    }
}