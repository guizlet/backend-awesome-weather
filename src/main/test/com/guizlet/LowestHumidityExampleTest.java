package com.guizlet;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.guizlet.shaobo.NewWeatherDataPoint;
import com.guizlet.shaobo.WeatherDataPoint;
import java.util.Optional;
import org.junit.Test;


public class LowestHumidityExampleTest {

  @Test
  public void testResult() {
    Optional<WeatherDataPoint> result = LowestHumidityExample.calculateLowestHumidity();
    double expected = 58;
    assertTrue(result.isPresent());
    // We accept some difference in results of MetaWeather and OpenWeatherMap
    assertEquals(expected, result.get().getHumidity(), 1);
    assertTrue(result.get() instanceof NewWeatherDataPoint);
    NewWeatherDataPoint weatherDataPoint = (NewWeatherDataPoint) result.get();
    assertTrue(weatherDataPoint.getSeaLevel() > 0);
  }
}