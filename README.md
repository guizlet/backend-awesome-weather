# Background
MetaWeather service has been well adopted internally. Its main responsibility is to fetch weather data from the third-party service https://www.metaweather.com which is in turn consumed by several downstream clients. Over time we start to realize that APIs from MetaWeather lack some features and do not meet our new requirements. Our goal is to migrate to APIs provided by https://openweathermap.org/api , which come with richer data sets, better documentation and more API parameters for finer-grained control.

Note: For simplicity, we'll refer to MetaWeather as "old service" and OpenWeatherMap as "new service" across all Java classes.

# Goals
* Improve code design in this project so that we will modify less code when migrating to yet another weather service in the future;
* Migrate the example code `LowestHumidityExample.java` to OpenWeatherMap APIs;
* Use existing unit tests to verify migration results. 

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
* Only 3 day forecast for San Francisco from Feb 12, 2019 are provided in the mock responses. This assumption is made across the entire code base; 
* Temperature unit is Celsius in the mock responses for consistency -- MetaWeather supports Celsius only while OpenWeatherMap supports all temperature units (one more reason to migrate!);
* Both MetaWeather and OpenWeatherMap APIs return multiple forecast data points per day instead of daily summaries. Information like lowest humidity or highest temperature is derived from aggregated results of raw data (That piece of code is provided).
* Weather data from MetaWeather and OpenWeatherMap is not identical. It's more than acceptable to observe slightly different results after the migration.

# Submit your solution
Before you start, create a new branch for your solution:
```
git checkout -b "interview/${USER}"
```
Submitting the solution:
```
git push -u origin "interview/${USER}"
```

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
* JDK: 1.8

# References
## MetaWeather API Doc
https://www.metaweather.com/api/

## OpenWeatherMap API Doc
https://openweathermap.org/forecast5

## Java library - org.json:json
https://github.com/stleary/JSON-java
