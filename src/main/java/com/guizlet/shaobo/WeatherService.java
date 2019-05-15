package com.guizlet.shaobo;

import java.time.LocalDate;
import java.util.List;

public interface WeatherService {

  List<WeatherDailyForecast> getNext3DaysForecast(String city, LocalDate date);

}
