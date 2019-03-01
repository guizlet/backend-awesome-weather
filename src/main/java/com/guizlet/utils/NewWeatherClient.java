package com.guizlet.utils;

import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;


public class NewWeatherClient {

    private static final String API_BASE_URL = "http://api.openweathermap.org/data/2.5/forecast";
    private static final String CITIES_FILE = "openweathermap_cities.json";
    private static final String JSON_KEY_CITY_NAME = "name";
    private static final String JSON_KEY_CITY_ID = "id";

    public String sendRequest(String city) {
        Integer cityId = resolveCityId(city);
        // "units=metric" to set temperature unit to Celsius
        String urlString = API_BASE_URL + "?id=" + cityId + "&units=metric";

        return MockNewWeatherServer.respond(urlString);
    }

    /**
     * Resolve a city Id based on the requested city name.
     *
     * @param city city name
     * @return resolved city Id
     * @throws NoSuchElementException if no city Id is found matching the city name
     */
    private int resolveCityId(String city) {
        String fileContent = Utils.readFromFile(CITIES_FILE);
        JSONTokener jsonTokener = new JSONTokener(fileContent);
        JSONArray jsonArray = new JSONArray(jsonTokener);
        Integer cityId = null;
        for (Object cityInfo : jsonArray) {
            JSONObject cityJsonObject = (JSONObject) cityInfo;

            if (cityJsonObject.getString(JSON_KEY_CITY_NAME).equals(city)) {
                cityId = cityJsonObject.getInt(JSON_KEY_CITY_ID);
                break;
            }
        }
        if (cityId == null) {
            throw new NoSuchElementException("No city found for the given input: " + city);
        }
        return cityId;
    }
}
