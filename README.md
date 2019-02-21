# Background
MetaWeather service has been well adopted internally. It fetches weather data from the third-party service https://www.metaweather.com which is in turn used by several clients. Over time we start to realize that APIs from MetaWeather lack of enough features and do not meet our new requirements. Our goal is to migrate to APIs provided by https://openweathermap.org/api which provide richer data sets, better documentation and more API parameters for finer-grained control.

# Migration goals
* Migrate client code (ClientCodeExample[0-9]) to OpenWeatherMap APIs.

# Where to start
* `ClientCodeExample[0-9].java`: where migration will take place;
* `openweathermap_3_days_forecast_2019_02_12.json`: the mock response of OpenWeatherMap APIs;
* `OpenWeatherMapService.java`: util functions for parsing the mock response of OpenWeatherMap.  

# About API responses
* Responses from MetaWeather or OpenWeatherMap are mocked in this repo. There will be no API calls when executing the code;
* Only 3 day forecast for San Francisco from Feb 12, 2019 are provided in the mock responses. This assumption is made across the entire code base; 
* Temperature unit is Celsius in the mock responses for consistency -- MetaWeather supports Celsisu only while OpenWeatherMap supports all temperature units (one more reason to migrate!);
* Both MetaWeather and OpenWeatherMap APIs return multiple forecast data points per day instead of daily summaries. There are some aggregation work required to derive information like the highest temperature or the lowest humidity of a given day;
* Weather data from MetaWeather and OpenWeatherMap are not identical. It's more than acceptable to observe slightly different results after the migration.

# System requirements
* JDK: 1.8
* Gradle: https://gradle.org/install/

# How to run example code
## Command line
```
$ gradle runExample1
...
Lowest humidity date: 2019-02-14
Lowest humidity: 58

$ gradle runExample2
...
Average high for next 3 days in San Francisco: 13.984444444444444
```

## IntelliJ IDEA
Open this folder in IntelliJ.

# References
## MetaWeather API Doc
https://www.metaweather.com/api/

## OpenWeatherMap API Doc
https://openweathermap.org/forecast5

## Java library - org.json:json
https://github.com/stleary/JSON-java
