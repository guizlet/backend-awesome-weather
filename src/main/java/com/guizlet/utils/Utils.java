package com.guizlet.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.stream.Collectors;


public class Utils {

    /**
     * Mock an HTTP request to MetaWeather or OpenWeatherMap servers
     *
     * @param urlString Url in String
     * @return Response in String
     */
    public static String sendRequest(String urlString) {
        if (urlString.contains("metaweather.com")) {
            return MockMetaWeatherServer.respond(urlString);
        } else if (urlString.contains("openweathermap.org")) {
            return MockOpenWeatherMapServer.respond(urlString);
        } else {
            throw new IllegalArgumentException("Illegal URLs");
        }
    }

    static String readFromFile(String fileName) {
        InputStream inputStream = Utils.class.getResourceAsStream("/" + fileName);
        return new BufferedReader(new InputStreamReader(inputStream)).lines().collect(Collectors.joining("\n"));
    }
}
