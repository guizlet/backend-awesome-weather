package com.guizlet.metaweather;

import com.guizlet.utils.Constants;
import com.guizlet.utils.OldWeatherClient;
import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.StreamSupport;
import org.json.JSONArray;
import org.json.JSONObject;


public class OldWeatherService {

    private static final String JSON_KEY_APPLICABLE_DATE = "applicable_date";
    private static final String JSON_KEY_MAX_TEMP = "max_temp";
    private static final String JSON_KEY_HUMIDITY = "humidity";

    private final OldWeatherClient oldWeatherClient;

    public OldWeatherService(OldWeatherClient oldWeatherClient) {
        this.oldWeatherClient = oldWeatherClient;
    }

    public List<OldWeatherDailyForecast> getNext3DaysForecast(String city, LocalDate requestedDate) {
        return IntStream.rangeClosed(1, Constants.FORECAST_DAYS).mapToObj(nextDay -> {
            LocalDate date = requestedDate.plusDays(nextDay);
            return getForecastForCityAndDate(city, date);
        }).collect(Collectors.toList());
    }

    /**
     * Get weather forecast for a given city name and a date.
     *
     * @param city city name
     * @param date requested date
     * @return Daily forecast of the requested date in the form of {@link OldWeatherDailyForecast}
     */
    private OldWeatherDailyForecast getForecastForCityAndDate(String city, LocalDate date) {
        String response = oldWeatherClient.sendRequest(city, date);
        JSONArray dailyWeather = new JSONArray(response);

        List<OldWeatherDataPoint> dataPoints = StreamSupport.stream(dailyWeather.spliterator(), false).map(forecast -> {
            JSONObject forecastJson = (JSONObject) forecast;
            String applicableDate = forecastJson.getString(JSON_KEY_APPLICABLE_DATE);
            double maxTemp = forecastJson.getDouble(JSON_KEY_MAX_TEMP);
            int humidity = forecastJson.getInt(JSON_KEY_HUMIDITY);
            return new OldWeatherDataPoint(applicableDate, humidity, maxTemp);
        }).collect(Collectors.toList());

        return new OldWeatherDailyForecast(dataPoints, date);
    }
}