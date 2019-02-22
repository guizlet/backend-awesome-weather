package com.guizlet;

import com.guizlet.utils.Constants;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.guizlet.metaweather.OldWeatherDailyForecast;
import com.guizlet.metaweather.OldWeatherDataPoint;
import com.guizlet.metaweather.OldWeatherService;


/**
 * This client finds the day with the lowest humidity among next 3 days.
 */
public class LowestHumidityExample {
    public static void main(String[] args) {
        OldWeatherService oldWeatherService = new OldWeatherService();
        List<OldWeatherDailyForecast> next3DaysForecast = new ArrayList<>();
        String city = "San Francisco";
        LocalDate currentDate = Constants.TODAY;

        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            OldWeatherDailyForecast forecast = oldWeatherService.getForecastForCityAndDate(city, currentDate);
            next3DaysForecast.add(forecast);
        }

        LocalDate lowestHumidityDate = null;
        int lowestHumidity = Integer.MAX_VALUE;

        for (OldWeatherDailyForecast dailyForecast : next3DaysForecast) {
            for (OldWeatherDataPoint dataPoint : dailyForecast.getDataPoints()) {
                int humidity = dataPoint.getHumidity();
                if (humidity < lowestHumidity) {
                    lowestHumidity = humidity;
                    lowestHumidityDate = dailyForecast.getDate();
                }
            }
        }

        System.out.println("Lowest humidity date: " + lowestHumidityDate);
        System.out.println("Lowest humidity: " + lowestHumidity);
    }
}
