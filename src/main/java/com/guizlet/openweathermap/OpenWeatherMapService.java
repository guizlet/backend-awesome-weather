package com.guizlet.openweathermap;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;
import com.guizlet.utils.Utils;


/**
 * Solution
 */
public class OpenWeatherMapService {

    private static final String OPENWEATHERMAP_API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    /**
     * Sample code of parsing Json response from OpenWeatherMap. It's left incomplete for demonstration purpose.
     * @param jsonResponse
     */
    public void parseJsonReseponse(String jsonResponse) {
        // Json response contains forecast data points for next 5 days
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray dataPoints = jsonObject.getJSONArray("list");

        for (Object dataPoint : dataPoints) {
            JSONObject dataPointJsonObject = (JSONObject) dataPoint;
            String date = dataPointJsonObject.getString("dt_txt");
            JSONObject main = dataPointJsonObject.getJSONObject("main");
            Double measuredHighTemp = main.getDouble("temp");
        }
    }

    /**
     * If today is Feb 12, 2019, this API will provide forecast from OpenWeatherMap for the next 5 days (Feb 13-17):
     * API doc: https://openweathermap.org/forecast5
     * Response: openweathermap_5_days_forecast_2019_02_12.json
     *
     * @param city
     * @return
     */
    public String getNext5DaysPredictionForCity(String city) {
        Integer cityId = resolveCityId(city);
        // "units=metric" to set temperature unit to Celsius
        String urlString = OPENWEATHERMAP_API_BASE_URL + "?id=" + cityId + "&units=metric";
        return Utils.sendRequest(urlString);
    }

    /**
     * Resolve the city Id based on the city name.
     *
     * @param city city name
     * @return resolved city Id
     */
    private int resolveCityId(String city) {
        InputStream cityInputStream = null;
        try {
            cityInputStream = new FileInputStream("openweathermap_cities.json");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        JSONTokener jsonTokener = new JSONTokener(cityInputStream);
        JSONArray jsonArray = new JSONArray(jsonTokener);
        Integer cityId = null;
        for (Object cityInfo : jsonArray) {
            JSONObject cityJsonObject = (JSONObject) cityInfo;
            if (cityJsonObject.getString("name").equals(city)) {
                cityId = cityJsonObject.getInt("id");
                break;
            }
        }
        if (cityId == null) {
            throw new NoSuchElementException("No city found for the given input: " + city);
        }
        return cityId;
    }
}
