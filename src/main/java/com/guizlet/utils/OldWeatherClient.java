package com.guizlet.utils;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.NoSuchElementException;
import org.json.JSONArray;
import org.json.JSONObject;


public class OldWeatherClient {

    private static final String API_BASE_URL = "https://www.metaweather.com/api/";
    private static final String LOCATION_API_PATH = "location/";
    private static final String CITY_API_PATH = "location/search/?query=";
    private static final String ENCODING_UTF_8 = "UTF-8";
    private static final String META_WEATHER_DATE_FORMAT = "yyyy/MM/dd";
    private static final String JSON_KEY_CITY_ID = "woeid";

    public String sendRequest(String city, LocalDate date) {
        int cityId = resolveCityID(city);

        String dateString = date.format(DateTimeFormatter.ofPattern(META_WEATHER_DATE_FORMAT));
        String weatherAPIUrl = API_BASE_URL + LOCATION_API_PATH + cityId + "/" + dateString;

        return MockOldWeatherServer.respond(weatherAPIUrl);
    }

    private int resolveCityID(String city) {
        String encodedCityName;
        try {
            encodedCityName = URLEncoder.encode(city, ENCODING_UTF_8);
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException("Unable to encode city: " + city);
        }
        String cityAPIUrl = API_BASE_URL + CITY_API_PATH + encodedCityName;
        String response = MockOldWeatherServer.respond(cityAPIUrl);
        JSONArray jsonArray = new JSONArray(response);
        if (jsonArray.isEmpty()) {
            throw new NoSuchElementException("No city found for the given city name: " + city);
        }
        JSONObject jsonObject = (JSONObject) jsonArray.get(0);
        return jsonObject.getInt(JSON_KEY_CITY_ID);
    }
}