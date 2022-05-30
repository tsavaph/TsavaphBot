package bots.tsavaph.weather.yandex;

class Forecast {
    private String sunrise;
    private String sunset;

    Forecast(String sunrise, String sunset) {
        this.sunrise = sunrise;
        this.sunset = sunset;
    }

    public String getSunrise() {
        return sunrise;
    }

    public String getSunset() {
        return sunset;
    }
}
