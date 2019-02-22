package com.guizlet.openweathermap;

import java.time.LocalDate;
import java.util.List;


public class NewWeatherDailyForecast {
    private List<NewWeatherDataPoint> dataPoints;
    private LocalDate date;

    public NewWeatherDailyForecast(List<NewWeatherDataPoint> dataPoints, LocalDate date) {
        this.dataPoints = dataPoints;
        this.date = date;
    }

    public List<NewWeatherDataPoint> getDataPoints() {
        return dataPoints;
    }

    public LocalDate getDate() {
        return date;
    }
}
