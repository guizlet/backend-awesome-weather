package com.guizlet.metaweather;

public class OldWeatherDataPoint {
    private String applicableDate;
    private int humidity;
    private double maxTemp;

    OldWeatherDataPoint(String applicableDate, int humidity, double maxTemp) {
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
