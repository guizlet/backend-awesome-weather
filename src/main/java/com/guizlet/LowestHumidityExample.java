package com.guizlet;

import com.guizlet.metaweather.OldWeatherDailyForecast;
import com.guizlet.metaweather.OldWeatherDataPoint;
import com.guizlet.metaweather.OldWeatherService;
import com.guizlet.utils.Constants;
import com.guizlet.utils.OldWeatherClient;
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
    Optional<OldWeatherDataPoint> dataPointWithLowestHumidity = calculateLowestHumidity();

    dataPointWithLowestHumidity.ifPresent(minHumidityDataPoint -> {
      System.out.println("Lowest humidity: " + minHumidityDataPoint.getHumidity());
      System.out.println("Lowest humidity date: " + minHumidityDataPoint.getApplicableDate());
    });
  }

  static Optional<OldWeatherDataPoint> calculateLowestHumidity() {
    OldWeatherService oldWeatherService = new OldWeatherService(new OldWeatherClient());

    List<OldWeatherDailyForecast> next3DaysForecast =
        oldWeatherService.getNext3DaysForecast(Constants.CITY_SAN_FRANCISCO, Constants.TODAY);

    return next3DaysForecast.stream()
        .flatMap(dailyForecast -> dailyForecast.getDataPoints().stream())
        .min(Comparator.comparing(OldWeatherDataPoint::getHumidity));
  }
}
