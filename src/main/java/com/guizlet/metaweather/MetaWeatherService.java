package com.guizlet.metaweather;

import com.guizlet.utils.Utils;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;
import org.json.JSONArray;
import org.json.JSONObject;


public class MetaWeatherService {

    private static final String METAWEATHER_API_BASE_URL = "https://www.metaweather.com/api/";

    /**
     * Get weather forecast for a given city name and a date.
     *
     * @param city city name
     * @param date requested date
     * @return Daily forecast of the requested date in the form of {@link MetaWeatherDailyForecast}
     */
    public MetaWeatherDailyForecast getForecastForCityAndDate(String city, LocalDate date) {
        int cityId = resolveCityID(city);
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String weatherAPIUrl = METAWEATHER_API_BASE_URL + "location/" + cityId + "/" + dateString;
        String response = Utils.sendRequest(weatherAPIUrl);
        JSONArray dailyWeather = new JSONArray(response);
        List<MetaWeatherDataPoint> dataPoints = new ArrayList<>();
        for (Object forecast : dailyWeather) {
            JSONObject forecastJson = (JSONObject) forecast;
            double maxTemp = forecastJson.getDouble("max_temp");
            double minTemp = forecastJson.getDouble("min_temp");
            double windSpeed = forecastJson.getDouble("wind_speed");
            double airPressure = forecastJson.getDouble("air_pressure");
            int humidity = forecastJson.getInt("humidity");
            String createdTime = forecastJson.getString("created");
            MetaWeatherDataPoint metaWeatherDataPoint =
                    new MetaWeatherDataPoint(airPressure, createdTime, humidity, minTemp, maxTemp, windSpeed);
            dataPoints.add(metaWeatherDataPoint);
        }

        return new MetaWeatherDailyForecast(dataPoints, date);
    }

    /**
     * Calculate the average high temperature from a set of {@link MetaWeatherDailyForecast}. For example, if the set contains
     * daily forecast of Feb 13 and Feb 14, and the high of these two days are 27 and 29 respectively, then the result would be 28.
     *
     * @param dailyForecastSet
     * @return
     */
    public Double getAverageHighTemperature(Set<MetaWeatherDailyForecast> dailyForecastSet) {
        double sum = 0.0;
        int setSize = dailyForecastSet.size();
        for (MetaWeatherDailyForecast dailyForecast : dailyForecastSet) {
            double dailyHigh = Double.MIN_VALUE;
            List<MetaWeatherDataPoint> dataPoints = dailyForecast.getDataPoints();
            for (MetaWeatherDataPoint metaWeatherDataPoint : dataPoints) {
                double maxTemp = metaWeatherDataPoint.getMaxTemp();
                dailyHigh = maxTemp > dailyHigh ? maxTemp : dailyHigh;
            }
            sum += dailyHigh;
        }

        return sum / setSize;
    }

    private int resolveCityID(String city) {
        String cityAPIUrl = null;
        try {
            cityAPIUrl = METAWEATHER_API_BASE_URL + "location/search/?query=" + URLEncoder.encode(city, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String response = Utils.sendRequest(cityAPIUrl);
        JSONArray jsonArray = new JSONArray(response);
        if (jsonArray.isEmpty()) {
            throw new NoSuchElementException("No city found for the given city name: " + city);
        }
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return jsonObject.getInt("woeid");
    }
}