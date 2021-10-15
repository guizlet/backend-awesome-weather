# Background
Our app currently relies internally on the API of a third-party service, MetaWeather (https://www.metaweather.com). It fetches weather data from this service, which are in turn consumed by several clients. Over time we've been finding that the MetaWeather API lacks some features and does not meet all of our new requirements. Our goal is to migrate to an API provided by OpenWeather (https://openweathermap.org/api), which comes with richer data sets, better documentation and more options for finer-grained control.

Note: For simplicity, we'll refer to MetaWeather as "old service" and OpenWeatherMap as "new service" across all Java classes.

# Goals
* Migrate the example code `LowestHumidityExample.java` to OpenWeatherMap APIs;
* Improve the code design so that it will be easier to modify our code the next time, if we ever need to migrate to yet another weather service in the future;
* Use existing unit tests to verify migration results.

## Bonus goals
* Our data scientists have been trying to figure out the correlation between humidity and [sea-level pressure](https://en.wikipedia.org/wiki/Atmospheric_pressure#Mean_sea-level_pressure). They can't make any progress, because information about sea-level pressure is not available in the old weather service. The good news is that it is available with the new one! Let's print out the sea-level pressure of the lowest humidity data point in `LowestHumidityExample.java` based on the code design you came up with earlier.
* Explain how to release your changes in a safe and backward-compatible way (the old weather service is still serving production traffic).

# File structure
## src/main/java
* Java class `LowestHumidityExample.java`: where migration will take place;
* Java class `AverageHighTemperatureExample.java`: other example(s) that illustrate how the old weather service is being used. Migration is *not* required in this interview;
* Java package `metaweather` and `openweathermap`: MetaWeather and OpenWeatherMap service classes and data models;
* Java package `util`: util classes and functions which are *not* in the critical path of the migration.

## src/main/resources
* Json file `metaweather_san_francisco_weather_2019_02_13.json`: the mock response of MetaWeather APIs;
* Json file `openweathermap_3_days_forecast_2019_02_12.json`: the mock response of OpenWeatherMap APIs.

## src/main/test
* Java class `LowestHumidityExampleTest.java` and `AverageHighTemperatureExampleTest.java`: unit tests.

# About code and API responses
* Responses from MetaWeather or OpenWeatherMap are mocked in this repo. There will be no actual API calls when the code is executed;
* Only 3-day forecasts for San Francisco from Feb 12, 2019 are provided in the mock responses. This assumption is made across the entire code base;
* Temperature unit is Celsius in the mock responses for consistency. MetaWeather supports Celsius only, while OpenWeatherMap supports all temperature units (one more reason to migrate!);
* Both MetaWeather and OpenWeatherMap APIs return multiple forecast data points per day instead of daily summaries. Information like lowest humidity or highest temperature is derived from aggregated results of raw data (That piece of code is provided).
* Weather data from MetaWeather and OpenWeatherMap is not identical. It's perfectly acceptable to have slightly different results after the migration.

# Prepare your solution
Before you start, you might want to create a new local branch for your solution:
```
git checkout -b "interview/${USER}"
```

You can review your changes with your interviewer, whether in person or by sharing your screen.

# Run tests
Run unit tests
```
$ ./gradlew test

BUILD SUCCESSFUL in 0s
4 actionable tasks: 4 up-to-date
```
Run LowestHumidityExample:
```
$ ./gradlew runLowestHumidityExample
...
Lowest humidity date: 2019-02-14
Lowest humidity: 58
```
Run AverageHighTemperatureExample:
```
$ ./gradlew runAverageHighTemperatureExample
...
Average high for next 3 days in San Francisco: 13.984444444444444
```

# System requirements
* JDK: 1.8 or later

# References
## MetaWeather API Doc
https://www.metaweather.com/api/

## OpenWeatherMap API Doc
https://openweathermap.org/forecast5

## Java library - org.json:json
https://github.com/stleary/JSON-java
