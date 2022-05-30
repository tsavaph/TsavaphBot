package bots.tsavaph.weather.yandex;

class Fact {
    private int temp;
    private int feels_like;
    private int tempWater;
    private String condition;
    private double wind_speed;
    private int humidity;


    Fact(int temp, int feels_like, int temp_water, String condition, double wind_speed, int humidity) {
        this.temp = temp;
        this.feels_like = feels_like;
        this.tempWater = temp_water;
        this.condition = condition;
        this.wind_speed = wind_speed;
        this.humidity = humidity;
    }

    public int getTemp() {
        return temp;
    }

    public int getFeelsLike() {
        return feels_like;
    }

    public int getTempWater() {
        return tempWater;
    }

    public String getCondition() {
        String ruCondition = "как обычно грустно";

        switch (condition) {
            case "clear" :
                ruCondition = "ясно ☀️";
                break;

            case "partly-cloudy" :
                ruCondition = "малооблачно \uD83C\uDF24";
                break;

            case "cloudy" :
                ruCondition = "облачно с прояснениями ⛅️";
                break;

            case "overcast":
                ruCondition = "пасмурно ☁️";
                break;

            case "drizzle":
                ruCondition = "морось \uD83C\uDF27";
                break;

            case "light-rain":
                ruCondition = "небольшой дождь \uD83C\uDF27";
                break;

            case "rain":
                ruCondition = "дождь \uD83C\uDF27";
                break;

            case "moderate-rain":
                ruCondition = "умеренно сильный дождь \uD83C\uDF27";
                break;

            case "heavy-rain":
                ruCondition = "сильный дождь ☔️";
                break;

            case "continuous-heavy-rain":
                ruCondition = "длительный сильный дождь ☔️";
                break;

            case "showers":
                ruCondition = "ливень ☔️";
                break;

            case "wet-snow":
                ruCondition = "дождь со снегом \uD83C\uDF27";
                break;

            case "light-snow":
                ruCondition = "небольшой снег ❄️";
                break;

            case "snow":
                ruCondition = "снег ❄️";
                break;

            case "snow-showers":
                ruCondition = "снегопад \uD83C\uDF28";
                break;

            case "hail":
                ruCondition = "град \uD83C\uDF28";
                break;

            case "thunderstorm":
                ruCondition = "гроза \uD83C\uDF29";
                break;

            case "thunderstorm-with-rain":
                ruCondition = "дождь с грозой ⛈";
                break;

            case "thunderstorm-with-hail":
                ruCondition = "гроза с градом ⛈";
                break;
            default:
                ruCondition = "Что-то непонятное - баг";
        }

        return ruCondition;
    }

    public double getWindSpeed() {
        return wind_speed;
    }

    public int getHumidity() {
        return humidity;
    }
}
