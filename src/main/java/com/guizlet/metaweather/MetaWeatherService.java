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

    public MetaWeatherDailyPredication getPredictionForCityAndDate(String city, LocalDate date) {
        int cityId = resolveCityID(city);
        String dateString = date.format(DateTimeFormatter.ofPattern("yyyy/MM/dd"));
        String weatherAPIUrl = METAWEATHER_API_BASE_URL + "location/" + cityId + "/" + dateString;
        String response = Utils.sendRequest(weatherAPIUrl);
        JSONArray dailyWeather = new JSONArray(response);
        List<MetaWeatherDataPoint> dataPoints = new ArrayList<>();
        for (Object predication : dailyWeather) {
            JSONObject predicationJson = (JSONObject) predication;
            double maxTemp = predicationJson.getDouble("max_temp");
            double minTemp = predicationJson.getDouble("min_temp");
            double windSpeed = predicationJson.getDouble("wind_speed");
            double airPressure = predicationJson.getDouble("air_pressure");
            int humidity = predicationJson.getInt("humidity");
            String createdTime = predicationJson.getString("created");
            MetaWeatherDataPoint metaWeatherDataPoint =
                    new MetaWeatherDataPoint(airPressure, createdTime, humidity, minTemp, maxTemp, windSpeed);
            dataPoints.add(metaWeatherDataPoint);
        }

        return new MetaWeatherDailyPredication(dataPoints, date);
    }

    public Double getAverageHighTemperature(Set<MetaWeatherDailyPredication> set) {
        double sum = 0.0;
        int setSize = set.size();
        for (MetaWeatherDailyPredication dailyPredication : set) {
            double dailyHigh = Double.MIN_VALUE;
            List<MetaWeatherDataPoint> dataPoints = dailyPredication.getDataPoints();
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