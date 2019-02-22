# Background
MetaWeather service has been well adopted internally. Its main responsibility is to fetch weather data from the third-party service https://www.metaweather.com which is in turn consumed by several downstream clients. Over time we start to realize that APIs from MetaWeather lack of enough features and do not meet our new requirements. Our goal is to migrate to APIs provided by https://openweathermap.org/api which provide richer data sets, better documentation and more API parameters for finer-grained control.

# Migration goals
* Migrate example code to OpenWeatherMap APIs.

# Where to start
* `LowestHumidityExample.java` and `AverageHighTemperatureExample.java`: where migration will take place;
* `openweathermap_3_days_forecast_2019_02_12.json`: the mock response of OpenWeatherMap APIs;
* `OpenWeatherMapService.java`: util functions for parsing the mock response of OpenWeatherMap.  

# About code and API responses
* Responses from MetaWeather or OpenWeatherMap are mocked in this repo. There will be no actual API calls when the code is executed;
* Only 3 day forecast for San Francisco from Feb 12, 2019 are provided in the mock responses. This assumption is made across the entire code base; 
* Temperature unit is Celsius in the mock responses for consistency -- MetaWeather supports Celsius only while OpenWeatherMap supports all temperature units (one more reason to migrate!);
* Both MetaWeather and OpenWeatherMap APIs return multiple forecast data points per day instead of daily summaries. Information like lowest humidity or highest temperature is derived from raw data (That piece of code is provided).
* Weather data from MetaWeather and OpenWeatherMap are not identical. It's more than acceptable to observe slightly different results after the migration.
* For the purpose of code readability, we'll refer to MetaWeather as "old service" and OpenWeatherMap as "new service" in Java classes. 

# System requirements
* JDK: 1.8
* Gradle: https://gradle.org/install/

# How to run example code
## Command line
Run LowestHumidityExample:
```
$ gradle runLowestHumidityExample
...
Lowest humidity date: 2019-02-14
Lowest humidity: 58
```
Run AverageHighTemperatureExample:
```
$ gradle runAverageHighTemperatureExample
...
Average high for next 3 days in San Francisco: 13.984444444444444
```

## IntelliJ IDEA
Open this folder in IntelliJ

# References
## MetaWeather API Doc
https://www.metaweather.com/api/

## OpenWeatherMap API Doc
https://openweathermap.org/forecast5

## Java library - org.json:json
https://github.com/stleary/JSON-java
