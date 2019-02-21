package com.guizlet;

import com.guizlet.metaweather.MetaWeatherDailyForecast;
import com.guizlet.metaweather.MetaWeatherService;
import com.guizlet.utils.Constants;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * This client calculates average high temperatures of next 3 day.
 */
public class ClientCodeExample2 {
    public static void main(String[] args) {
        MetaWeatherService metaWeatherService = new MetaWeatherService();
        Set<MetaWeatherDailyForecast> next3DaysForecast = new HashSet<>();

        LocalDate currentDate = Constants.TODAY;
        String city = "San Francisco";

        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            MetaWeatherDailyForecast forecast = metaWeatherService.getForecastForCityAndDate(city, currentDate);
            next3DaysForecast.add(forecast);
        }

        Double averageHighTemperature = metaWeatherService.getAverageHighTemperature(next3DaysForecast);
        System.out.println("Average high for next 3 days in " + city + ": " + averageHighTemperature);
    }
}
