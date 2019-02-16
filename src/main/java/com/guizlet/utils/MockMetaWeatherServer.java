package com.guizlet.utils;

import java.util.HashMap;
import java.util.Map;


class MockMetaWeatherServer {

    private static final Map<String, String> validUrlsToFileNames = new HashMap<>();

    static {
        validUrlsToFileNames.put("https://www.metaweather.com/api/location/search/?query=San+Francisco",
                "metaweather_san_francisco_city.json");
        validUrlsToFileNames.put("https://www.metaweather.com/api/location/2487956/2019/02/13",
                "metaweather_san_francisco_weather_2019_02_13.json");
        validUrlsToFileNames.put("https://www.metaweather.com/api/location/2487956/2019/02/14",
                "metaweather_san_francisco_weather_2019_02_14.json");
        validUrlsToFileNames.put("https://www.metaweather.com/api/location/2487956/2019/02/15",
                "metaweather_san_francisco_weather_2019_02_15.json");
    }

    static String respond(String urlString) {
        if (!validUrlsToFileNames.keySet().contains(urlString)) {
            throw new IllegalArgumentException("Illegal URLs: " + urlString);
        }

        return Utils.readFromFile(validUrlsToFileNames.get(urlString));
    }
}
