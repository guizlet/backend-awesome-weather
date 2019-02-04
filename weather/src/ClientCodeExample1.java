import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import metaweather.MetaWeatherDailyPredication;
import metaweather.MetaWeatherDataPoint;
import metaweather.MetaWeatherService;


/**
 * In this code example, we assume that the city is San Francisco and the current date is Feb 12, 2019.
 * We only need to support weather forecast for next 3 days (Feb 13/14/15).
 */
public class ClientCodeExample1 {
    public static void main(String[] args) {
        MetaWeatherService metaWeatherService = new MetaWeatherService();
        List<MetaWeatherDailyPredication> next3DaysPredictions = new ArrayList<>();
        String city = "San Francisco";
        LocalDate currentDate = LocalDate.of(2019, 2, 12);
        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            MetaWeatherDailyPredication prediction = metaWeatherService.getPredictionForCityAndDate(city, currentDate);
            next3DaysPredictions.add(prediction);
        }

        LocalDate lowestHumidityDate = null;
        int lowestHumidity = Integer.MAX_VALUE;
        for (MetaWeatherDailyPredication dailyPredication : next3DaysPredictions) {
            for (MetaWeatherDataPoint dataPoint : dailyPredication.getDataPoints()) {
                int humidity = dataPoint.getHumidity();
                if (humidity < lowestHumidity) {
                    lowestHumidity = humidity;
                    lowestHumidityDate = dailyPredication.getDate();
                }
            }
        }

        System.out.println("Lowest humidity date: " + lowestHumidityDate);
        System.out.println("Lowest humidity: " + lowestHumidity);
    }
}
