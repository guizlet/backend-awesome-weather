package com.guizlet.openweathermap;

public class NewWeatherDataPoint {

    private final int humidity;
    private final double maxTemp;

    public NewWeatherDataPoint(int humidity, double maxTemp) {
        this.humidity = humidity;
        this.maxTemp = maxTemp;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getMaxTemp() {
        return maxTemp;
    }
}
