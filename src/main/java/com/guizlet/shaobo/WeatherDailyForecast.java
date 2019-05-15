package com.guizlet.shaobo;

import java.time.LocalDate;
import java.util.List;

public interface WeatherDailyForecast {

  List<WeatherDataPoint> getDataPoints();

  LocalDate getDate();

}
