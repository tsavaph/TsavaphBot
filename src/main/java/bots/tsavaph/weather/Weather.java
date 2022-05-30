package bots.tsavaph.weather;

import bots.tsavaph.weather.yandex.YandexWeather;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Weather {
    private final static String URL_NAME = "https://api.weather.yandex.ru/v2/informers?lang=ru_RU&";
    private double lat;
    private double lon;
    private String xYandexApiKey;

    public Weather(double lat, double lon, String xYandexApiKey) {
        this.lat = lat;
        this.lon = lon;
        this.xYandexApiKey = xYandexApiKey;
    }


    public String getWeatherInformation() throws IOException {
        URL url = new URL(URL_NAME + "lat=" + lat + "&lon=" + lon);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setRequestProperty("X-Yandex-API-Key", xYandexApiKey);
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            Gson gson = new Gson();
            String line;

            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);

            YandexWeather yandexWeather = gson.fromJson(stringBuilder.toString(), YandexWeather.class);

            connection.disconnect();
            return  "Температура воздуха: " + yandexWeather.getTemperature() + "°C" + "\n" +
                    "Ощущается как: " + yandexWeather.getFeelsLikeTemperature() + "°C" + "\n" +
                    "Скорость ветра : " + yandexWeather.getWindSpeed() + " м/с" + "\n" +
                    "Влажность : " + yandexWeather.getHumidity() + "%" + "\n" +
                    "На улице " + yandexWeather.getCondition() + "\n\n" +
                    "Подробнее по ссылке\n" + yandexWeather.getUrl();
        } else {
            connection.disconnect();
            return "К сожалению прогноз погоды недоступен. Если в течение 24 часов проблема не исчезнет, пишите @tsavaph";
        }
    }

}
