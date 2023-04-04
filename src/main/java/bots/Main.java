package bots;

import bots.tsavaph.TsavaphBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {
    public static final String BOT_USERNAME = "@TsavaphBot";
    public static final String BOT_TOKEN = "BOT_TOKENBOT_TOKENBOT_TOKENBOT_TOKENBOT_TOKEN";
    public static final String YANDEX_API_KEY = "YANDEX_API_KEYYANDEX_API_KEYYANDEX_API_KEYYANDEX_API_KEY";
    public static void main(String[] args) {

        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            TsavaphBot tsavaphBot = new TsavaphBot(
                    BOT_USERNAME,
                    BOT_TOKEN,
                    YANDEX_API_KEY);

            telegramBotsApi.registerBot(tsavaphBot);

        } catch (TelegramApiException e) {
            System.out.println(e);
        }

    }
}
