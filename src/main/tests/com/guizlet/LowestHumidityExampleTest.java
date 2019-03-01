package com.guizlet;

import com.guizlet.metaweather.OldWeatherDataPoint;
import java.util.Optional;
import org.junit.Test;

import static org.junit.Assert.*;


public class LowestHumidityExampleTest {
    @Test
    public static void testResult() {
        Optional<OldWeatherDataPoint> result = LowestHumidityExample.calculateLowestHumidity();
        double expected = 58;
        assertTrue(result.isPresent());
        // We accept some difference in results of MetaWeather and OpenWeatherMap
        assertEquals(expected, result.get().getHumidity(), 5);
    }
}