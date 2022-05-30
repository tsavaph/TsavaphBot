package bots.tsavaph.weather.yandex;

class Info {
    private double lat;
    private double lon;
    private String url;

    Info(double lat, double lon, String url) {
        this.lat = lat;
        this.lon = lon;
        this.url = url;
    }

    String getUrl() {
        return url;
    }
}
