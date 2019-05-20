package com.guizlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.guizlet.metaweather.OldWeatherDataPoint;
import java.util.Optional;
import org.junit.Test;


public class LowestHumidityExampleTest {

  @Test
  public void testResult() {
    Optional<OldWeatherDataPoint> result = LowestHumidityExample.calculateLowestHumidity();
    double expected = 58;
    assertTrue(result.isPresent());
    // We accept some difference in results of MetaWeather and OpenWeatherMap
    assertEquals(expected, result.get().getHumidity(), 1);
  }
}