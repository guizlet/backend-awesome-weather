package com.guizlet.openweathermap;

public class NewWeatherDataPoint {

  private final String applicableDate;
  private final int humidity;
  private final double maxTemp;
  private final double seaLevelPressure;

  NewWeatherDataPoint(String applicableDate, int humidity, double maxTemp, double seaLevelPressure) {
    this.applicableDate = applicableDate;
    this.humidity = humidity;
    this.maxTemp = maxTemp;
    this.seaLevelPressure = seaLevelPressure;
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

  public double getSeaLevelPressure() {
    return seaLevelPressure;
  }
}
