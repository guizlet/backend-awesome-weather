package com.guizlet.openweathermap;

import com.guizlet.utils.NewWeatherClient;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import java.util.stream.StreamSupport;
import org.json.JSONArray;
import org.json.JSONObject;


public class NewWeatherService {

  private static final String JSON_KEY_DATE = "dt_txt";
  private static final String JSON_KEY_MAIN = "main";
  private static final String JSON_KEY_LIST = "list";
  private static final String JSON_KEY_HUMIDITY = "humidity";
  private static final String JSON_KEY_TEMP_MAX = "temp_max";

  private final NewWeatherClient newWeatherClient;

  public NewWeatherService(NewWeatherClient newWeatherClient) {
    this.newWeatherClient = newWeatherClient;
  }

  /**
   * Get forecasts for each of the 3 days after <code>date</code> from openweathermap.org.
   *
   * @param city the requested city
   * @param date the requested date.
   * @return a list of {@link NewWeatherDailyForecast}. Each {@link NewWeatherDailyForecast}
   *     represents a daily forecast of next 3 days.
   */
  public List<NewWeatherDailyForecast> getNext3DaysForecast(String city, LocalDate date) {
    String jsonResponse = newWeatherClient.sendRequest(city, date);

    JSONObject jsonObject = new JSONObject(jsonResponse);
    JSONArray dataPoints = jsonObject.getJSONArray(JSON_KEY_LIST);

    Map<String, List<NewWeatherDataPoint>> dataPointsByDate = getNewWeatherDataPointStream(
        dataPoints).collect(
        Collectors.groupingBy(NewWeatherDataPoint::getApplicableDate));

    return dataPointsByDate.entrySet()
        .stream()
        .map(
            entry -> new NewWeatherDailyForecast(entry.getValue(), LocalDate.parse(entry.getKey())))
        .collect(Collectors.toList());
  }

  private Stream<NewWeatherDataPoint> getNewWeatherDataPointStream(JSONArray dataPoints) {
    return StreamSupport.stream(dataPoints.spliterator(), false).map(dataPoint -> {
      JSONObject dataPointJsonObject = (JSONObject) dataPoint;
      JSONObject mainDataBody = dataPointJsonObject.getJSONObject(JSON_KEY_MAIN);
      int humidity = mainDataBody.getInt(JSON_KEY_HUMIDITY);
      double tempMax = mainDataBody.getDouble(JSON_KEY_TEMP_MAX);
      // Date format from OpenWeatherMap: "2019-02-15 21:00:00"
      String applicableDate = dataPointJsonObject.getString(JSON_KEY_DATE).split(" ")[0];

      return new NewWeatherDataPoint(applicableDate, humidity, tempMax);
    });
  }
}
