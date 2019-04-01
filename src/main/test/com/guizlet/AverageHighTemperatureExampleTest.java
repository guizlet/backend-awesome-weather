package com.guizlet;

import static org.junit.Assert.assertEquals;

import org.junit.Test;


public class AverageHighTemperatureExampleTest {

  @Test
  public void testResult() {
    double result = AverageHighTemperatureExample.calculateAverageHighTemperature();
    double expected = 13.9999;
    // We accept some difference in results of MetaWeather and OpenWeatherMap
    assertEquals(expected, result, 1.0);
  }
}