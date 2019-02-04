import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import metaweather.MetaWeatherDailyPredication;
import metaweather.MetaWeatherService;


/**
 * In this code example, we assume that the city is San Francisco and the current date is Feb 12, 2019.
 * We only need to support weather forecast for next 3 days (Feb 13/14/15).
 */
public class ClientCodeExample2 {
    public static void main(String[] args) {
        MetaWeatherService metaWeatherService = new MetaWeatherService();
        Set<MetaWeatherDailyPredication> next3DaysPredictions = new HashSet<>();

        LocalDate currentDate = LocalDate.of(2019, 2, 12);
        String city = "San Francisco";
        for (int i = 0; i < 3; i++) {
            currentDate = currentDate.plusDays(1);
            MetaWeatherDailyPredication prediction = metaWeatherService.getPredictionForCityAndDate(city, currentDate);
            next3DaysPredictions.add(prediction);
        }
        Double averageHighTemperature = metaWeatherService.getAverageHighTemperature(next3DaysPredictions);
        System.out.println("Average high for next 3 days in " + city + ": " + averageHighTemperature);
    }
}
