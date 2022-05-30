package bots.tsavaph.photos;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public abstract class Photos {
    private final static String URL_NAME = "https://api.thecatapi.com/v1/images/search";

    public static String getRandomCatUrl() throws IOException {
        URL url = new URL(URL_NAME);

        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
        connection.setRequestMethod("GET");
        connection.setConnectTimeout(5000);
        connection.setReadTimeout(5000);
        connection.connect();

        if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
            InputStream stream = connection.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
            StringBuilder stringBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);

            JsonElement jsonElement = JsonParser.parseString(stringBuilder.substring(1, stringBuilder.length() - 1));

            JsonObject rootObject = jsonElement.getAsJsonObject();
            String urlString = rootObject.get("url").getAsString();

            connection.disconnect();
            return urlString;
        } else {
            return "";
        }
    }
}
