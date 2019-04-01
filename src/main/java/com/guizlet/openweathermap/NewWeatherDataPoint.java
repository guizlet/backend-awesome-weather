package com.guizlet.openweathermap;

public class NewWeatherDataPoint {

  private final String applicableDate;
  private final int humidity;
  private final double maxTemp;

  NewWeatherDataPoint(String applicableDate, int humidity, double maxTemp) {
    this.applicableDate = applicableDate;
    this.humidity = humidity;
    this.maxTemp = maxTemp;
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
}
