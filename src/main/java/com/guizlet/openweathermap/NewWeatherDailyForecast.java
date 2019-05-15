package com.guizlet.openweathermap;

import com.guizlet.shaobo.WeatherDailyForecast;
import com.guizlet.shaobo.WeatherDataPoint;
import java.time.LocalDate;
import java.util.List;


public class NewWeatherDailyForecast implements WeatherDailyForecast {

  private final List<WeatherDataPoint> dataPoints;
  private final LocalDate date;

  NewWeatherDailyForecast(List<WeatherDataPoint> dataPoints, LocalDate date) {
    this.dataPoints = dataPoints;
    this.date = date;
  }

  public List<WeatherDataPoint> getDataPoints() {
    return dataPoints;
  }

  public LocalDate getDate() {
    return date;
  }
}
