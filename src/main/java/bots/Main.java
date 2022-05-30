package bots;

import bots.cockbot.TsavaphCockSizeBot;
import bots.tsavaph.TsavaphBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args) {
        try {
            TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);

            TsavaphBot tsavaphBot = new TsavaphBot(
                    "@botUserName1",
                    "botToken1",
                    "xYandexApiKey1");

            TsavaphCockSizeBot tsavaphCockSizeBot = new TsavaphCockSizeBot(
                    "@botUserName2",
                    "botToken2");

            telegramBotsApi.registerBot(tsavaphBot);
            telegramBotsApi.registerBot(tsavaphCockSizeBot);

        } catch (TelegramApiException e) {
            System.out.println(e);
        }

    }
}
