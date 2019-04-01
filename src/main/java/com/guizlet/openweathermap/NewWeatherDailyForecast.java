package com.guizlet.openweathermap;

import java.time.LocalDate;
import java.util.List;


public class NewWeatherDailyForecast {

  private final List<NewWeatherDataPoint> dataPoints;
  private final LocalDate localDate;

  public NewWeatherDailyForecast(List<NewWeatherDataPoint> dataPoints, LocalDate localDate) {
    this.dataPoints = dataPoints;
    this.localDate = localDate;
  }

  public List<NewWeatherDataPoint> getDataPoints() {
    return dataPoints;
  }

  public LocalDate getLocalDate() {
    return localDate;
  }
}
