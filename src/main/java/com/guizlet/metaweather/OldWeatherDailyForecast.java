package com.guizlet.metaweather;

import java.time.LocalDate;
import java.util.List;


public class OldWeatherDailyForecast {

  private final LocalDate date;
  private final List<OldWeatherDataPoint> dataPoints;

  OldWeatherDailyForecast(List<OldWeatherDataPoint> dataPoints, LocalDate date) {
    this.dataPoints = dataPoints;
    this.date = date;
  }

  public List<OldWeatherDataPoint> getDataPoints() {
    return dataPoints;
  }

  public LocalDate getDate() {
    return date;
  }
}
