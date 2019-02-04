package utils;

import java.util.HashMap;
import java.util.Map;


class MockOpenWeatherMapServer {

    private static final Map<String, String> validUrlsToFileNames = new HashMap<>();

    static {
        validUrlsToFileNames.put("http://api.openweathermap.org/data/2.5/forecast?id=5391997&units=metric", "openweathermap_5_days_forecast_2019_02_12.json");
    }

    static String respond(String urlString) {
        if (!validUrlsToFileNames.keySet().contains(urlString)) {
            throw new IllegalArgumentException("Illegal URLs: " + urlString);
        }

        return Utils.readFromFile(validUrlsToFileNames.get(urlString));
    }
}
