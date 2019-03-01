package com.guizlet;

import org.junit.Test;

import static org.junit.Assert.*;


public class AvergeHighTemperatureExampleTest {

    @Test
    public static void testResult() {
        double result = AverageHighTemperatureExample.calculateAverageHighTemperature();
        double expected = 13.9999;
        // We accept some difference in results of MetaWeather and OpenWeatherMap
        assertEquals(expected, result, 1.0);
    }
}