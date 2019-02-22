package com.guizlet;

import com.guizlet.metaweather.OldWeatherDailyForecast;
import com.guizlet.metaweather.OldWeatherService;
import com.guizlet.utils.Constants;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


/**
 * This client calculates average high temperatures of next 3 day.
 */
public class AverageHighTemperatureExample {
    public static void main(String[] args) {
        OldWeatherService oldWeatherService = new OldWeatherService();
        Set<OldWeatherDailyForecast> next3DaysForecast = new HashSet<>();

        LocalDate currentDate = Constants.TODAY;
        String city = "San Francisco";

        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            OldWeatherDailyForecast forecast = oldWeatherService.getForecastForCityAndDate(city, currentDate);
            next3DaysForecast.add(forecast);
        }

        Double averageHighTemperature = oldWeatherService.getAverageHighTemperature(next3DaysForecast);
        System.out.println("Average high for next 3 days in " + city + ": " + averageHighTemperature);
    }
}
