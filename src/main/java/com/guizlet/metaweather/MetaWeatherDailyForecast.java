package com.guizlet.metaweather;

import java.time.LocalDate;
import java.util.List;


public class MetaWeatherDailyForecast {
    private List<MetaWeatherDataPoint> dataPoints;
    private LocalDate date;

    MetaWeatherDailyForecast(List<MetaWeatherDataPoint> dataPoints, LocalDate date) {
        this.dataPoints = dataPoints;
        this.date = date;
    }

    public List<MetaWeatherDataPoint> getDataPoints() {
        return dataPoints;
    }

    public LocalDate getDate() {
        return date;
    }
}
