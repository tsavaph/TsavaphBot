package bots.tsavaph.bitcoin;

import bots.tsavaph.bitcoin.blockchain.CurrencyInfo;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class BitcoinInfo {
    private final static String URL_NAME = "https://blockchain.info/ticker";

    public static String getCurrencyInfo() throws IOException {
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
            Gson gson = new Gson();
            String line;

            while ((line = reader.readLine()) != null)
                stringBuilder.append(line);

            CurrencyInfo currencyInfo = gson.fromJson(stringBuilder.toString(), CurrencyInfo.class);
            connection.disconnect();
            return "Курс биткоина: ₿1 = $" + currencyInfo.getLastInfo();
        } else {
            connection.disconnect();
            return "К сожалению, в данный момент курс биткоина не доступен";
        }
    }

}
