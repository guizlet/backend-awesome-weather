package metaweather;

public class MetaWeatherDataPoint {
    private double airPressure;
    private String createdTime;
    private int humidity;
    private double minTemp;
    private double maxTemp;
    private double windSpeed;

    public MetaWeatherDataPoint(double airPressure, String createdTime, int humidity, double minTemp, double maxTemp,
            double windSpeed) {
        this.airPressure = airPressure;
        this.createdTime = createdTime;
        this.humidity = humidity;
        this.minTemp = minTemp;
        this.maxTemp = maxTemp;
        this.windSpeed = windSpeed;
    }

    public double getAirPressure() {
        return airPressure;
    }

    public String getCreatedTime() {
        return createdTime;
    }

    public int getHumidity() {
        return humidity;
    }

    public double getMinTemp() {
        return minTemp;
    }

    public double getMaxTemp() {
        return maxTemp;
    }

    public double getWindSpeed() {
        return windSpeed;
    }
}
