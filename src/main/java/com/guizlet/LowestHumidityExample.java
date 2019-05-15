package com.guizlet;

import com.guizlet.openweathermap.NewWeatherService;
import com.guizlet.shaobo.NewWeatherDataPoint;
import com.guizlet.shaobo.WeatherDailyForecast;
import com.guizlet.shaobo.WeatherDataPoint;
import com.guizlet.shaobo.WeatherService;
import com.guizlet.utils.Constants;
import com.guizlet.utils.NewWeatherClient;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;


/**
 * This example finds the day with the lowest humidity among next 3 days.
 */
public class LowestHumidityExample {

  /**
   * Entrypoint for command line execution.
   *
   * @param args arguments supplied on command line.
   */
  public static void main(String[] args) {
    Optional<WeatherDataPoint> dataPointWithLowestHumidity = calculateLowestHumidity();

    dataPointWithLowestHumidity.ifPresent(lowestHumidityDataPoint -> {
      System.out.println("Lowest humidity: " + lowestHumidityDataPoint.getHumidity());
      System.out.println("Lowest humidity date: " + lowestHumidityDataPoint.getApplicableDate());
      // Bonus: display sea level from the lowest humidity data point
      if (lowestHumidityDataPoint instanceof NewWeatherDataPoint) {
        NewWeatherDataPoint newWeatherDataPoint = (NewWeatherDataPoint) lowestHumidityDataPoint;
        System.out.println("Lowest sea level: " + newWeatherDataPoint.getSeaLevel());
      }
    });
  }

  static Optional<WeatherDataPoint> calculateLowestHumidity() {
    WeatherService weatherService = new NewWeatherService(new NewWeatherClient());

    List<WeatherDailyForecast> next3DaysForecast =
        weatherService.getNext3DaysForecast(Constants.CITY_SAN_FRANCISCO, Constants.TODAY);

    return next3DaysForecast.stream()
        .flatMap(dailyForecast -> dailyForecast.getDataPoints().stream())
        .min(Comparator.comparing(WeatherDataPoint::getHumidity));
  }
}
