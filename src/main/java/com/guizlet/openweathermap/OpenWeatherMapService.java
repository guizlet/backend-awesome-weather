package com.guizlet.openweathermap;

import com.guizlet.utils.Utils;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class OpenWeatherMapService {

    private static final String OPENWEATHERMAP_API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";

    /**
     * Get weather forecast for a given city name and a date.
     *
     * @param city city name
     * @param date requested date
     * @return Daily forecast of the requested date in the form of a list of {@link JSONObject}
     */
    public List<JSONObject> getForecastForCityAndDate(String city, LocalDate date) {
        String jsonResponse = getNext3DaysForecastForCity(city);
        JSONObject jsonObject = new JSONObject(jsonResponse);
        JSONArray dataPoints = jsonObject.getJSONArray("list");

        String requestedDate = date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<JSONObject> results = new ArrayList<>();

        for (Object dataPoint : dataPoints) {
            JSONObject dataPointJsonObject = (JSONObject) dataPoint;
            String dateInResponse = dataPointJsonObject.getString("dt_txt");
            // Date format from OpenWeatherMap: "2019-02-15 21:00:00"
            if (dateInResponse.startsWith(requestedDate + " ")) {
                results.add(dataPointJsonObject);
            }
        }

        return results;
    }

    /**
     * This API derives a city Id based on the requested city name and provides forecast for next 3 days based
     * on the city Id.
     *
     * @param city city name
     * @return Json response of 3 day forecast of the resolved city Id
     */
    private String getNext3DaysForecastForCity(String city) {
        Integer cityId = resolveCityId(city);
        // "units=metric" to set temperature unit to Celsius
        String urlString = OPENWEATHERMAP_API_BASE_URL + "?id=" + cityId + "&units=metric";
        return Utils.sendRequest(urlString);
    }

    /**
     * Resolve a city Id based on the requested city name.
     *
     * @param city city name
     * @return resolved city Id
     * @throws NoSuchElementException if no city Id is found matching the city name
     */
    private int resolveCityId(String city) {
        String fileContent = Utils.readFromFile("openweathermap_cities.json");
        JSONTokener jsonTokener = new JSONTokener(fileContent);
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
