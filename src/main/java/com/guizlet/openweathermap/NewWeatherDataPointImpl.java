package com.guizlet.openweathermap;

import com.guizlet.shaobo.NewWeatherDataPoint;

public class NewWeatherDataPointImpl implements NewWeatherDataPoint {

  private final String applicableDate;
  private final int humidity;
  private final double maxTemp;
  private final double seaLevel;

  NewWeatherDataPointImpl(String applicableDate, int humidity, double maxTemp, double seaLevel) {
    this.applicableDate = applicableDate;
    this.humidity = humidity;
    this.maxTemp = maxTemp;
    this.seaLevel = seaLevel;
  }

  public String getApplicableDate() {
    return applicableDate;
  }

  public int getHumidity() {
    return humidity;
  }

  public double getMaxTemp() {
    return maxTemp;
  }

  public double getSeaLevel() {
    return seaLevel;
  }
}
