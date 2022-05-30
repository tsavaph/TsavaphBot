package bots.tsavaph.weather.yandex;

public class YandexWeather {

    private Info info;
    private Fact fact;
    private Forecast forecast;

    public YandexWeather(Info info, Fact fact, Forecast forecast) {
        this.info = info;
        this.fact = fact;
        this.forecast = forecast;
    }

    public String getUrl() {
        return info.getUrl();
    }

    public int getTemperature() {
        return fact.getTemp();
    }

    public int getFeelsLikeTemperature() {
        return fact.getFeelsLike();
    }

    public int getWaterTemperature() {
        return fact.getTempWater();
    }

    public String getCondition() {
        return fact.getCondition();
    }

    public double getWindSpeed() {
        return fact.getWindSpeed();
    }

    public int getHumidity() {
        return fact.getHumidity();
    }

    public String getSunriseTime() {
        return forecast.getSunrise();
    }

    public String getSunsetTime() {
        return forecast.getSunset();
    }
}
