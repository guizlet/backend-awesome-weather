package com.guizlet;

import com.guizlet.utils.Constants;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import com.guizlet.metaweather.MetaWeatherDailyForecast;
import com.guizlet.metaweather.MetaWeatherDataPoint;
import com.guizlet.metaweather.MetaWeatherService;


/**
 * This client finds the day with the lowest humidity among next 3 days.
 */
public class ClientCodeExample1 {
    public static void main(String[] args) {
        MetaWeatherService metaWeatherService = new MetaWeatherService();
        List<MetaWeatherDailyForecast> next3DaysForecast = new ArrayList<>();
        String city = "San Francisco";
        LocalDate currentDate = Constants.TODAY;

        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            MetaWeatherDailyForecast forecast = metaWeatherService.getForecastForCityAndDate(city, currentDate);
            next3DaysForecast.add(forecast);
        }

        LocalDate lowestHumidityDate = null;
        int lowestHumidity = Integer.MAX_VALUE;

        for (MetaWeatherDailyForecast dailyForecast : next3DaysForecast) {
            for (MetaWeatherDataPoint dataPoint : dailyForecast.getDataPoints()) {
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
