package com.guizlet;

import com.guizlet.metaweather.OldWeatherDailyForecast;
import com.guizlet.metaweather.OldWeatherDataPoint;
import com.guizlet.metaweather.OldWeatherService;
import com.guizlet.utils.Constants;
import com.guizlet.utils.OldWeatherClient;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * This example calculates average high temperatures of next 3 day.
 */
public class AverageHighTemperatureExample {

  public static void main(String[] args) {
    double average = calculateAverageHighTemperature();

    System.out.println("Average high for next 3 days: " + average);
  }

  static double calculateAverageHighTemperature() {
    OldWeatherService oldWeatherService = new OldWeatherService(new OldWeatherClient());

    List<OldWeatherDailyForecast> next3DaysForecast =
        oldWeatherService.getNext3DaysForecast(Constants.CITY_SAN_FRANCISCO, Constants.TODAY);

    Set<Double> dailyHighs = next3DaysForecast.stream()
        .map(dailyForecast -> dailyForecast.getDataPoints()
            .stream()
            .max(Comparator.comparing(OldWeatherDataPoint::getMaxTemp))
            .map(OldWeatherDataPoint::getMaxTemp))
        .filter(Optional::isPresent)
        .map(Optional::get)
        .collect(Collectors.toSet());

    double sum = dailyHighs.stream().mapToDouble(Double::doubleValue).sum();
    double size = dailyHighs.size();

    return sum / size;
  }
}
