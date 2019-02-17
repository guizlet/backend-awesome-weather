# Background
Awesome Weather service has been well adopted internally. It fetches weather data from the third-party service https://www.metaweather.com and processes them. Over time we start to realize that APIs from MetaWeather do not meet our new requirements. Our goal is to migrate to APIs provided by https://openweathermap.org/api which provide richer data sets, better documentation and more API parameters for finer-grained control. In the meantime, the migration should be transparent to existing clients. 

# Prerequisites
* JDK requirement: 1.8
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

# Caveats
* Responses from MetaWeather or OpenWeatherMap are mocked, which means there is no API call when running the code;
* Only 3 day forecast for San Francisco from Feb 12, 2019 are supported in the mock response. This assumption is reflected in the client code;
* Temperature unit is Celsius as it is the only option in MetaWeather (one more reason to migrate!)
* Both MetaWeather and OpenWeatherMap APIs return multiple prediction data points per day instead of daily summaries
  (which are more common in real life weather forecast). There will be some aggregation work required to derive information like the highest temperature or the lowest humidity over a day.
* Responses from MetaWeather and OpenWeatherMap are not exactly the same. In this interview, it's more than acceptable to see slightly different results after the migration.

# References
## MetaWeather API Doc
https://www.metaweather.com/api/

## OpenWeatherMap API Doc
https://openweathermap.org/forecast5

## org.json:json
https://github.com/stleary/JSON-java
