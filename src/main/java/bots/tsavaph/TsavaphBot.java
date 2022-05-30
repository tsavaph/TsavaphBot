package bots.tsavaph;

import bots.tsavaph.bitcoin.BitcoinInfo;
import bots.tsavaph.photos.Photos;
import bots.tsavaph.weather.Weather;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Location;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.*;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class TsavaphBot extends TelegramLongPollingBot {

    private String botUserName;
    private String botToken;
    private String xYandexApiKey;

    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public TsavaphBot(String botUserName, String botToken, String xYandexApiKey) {
        this.botUserName = botUserName;
        this.botToken = botToken;
        this.xYandexApiKey = xYandexApiKey;
    }

    @Override
    public void onUpdateReceived(Update update) {
        Message message = update.getMessage();

        if (update.hasInlineQuery()) {
            try {
                handleInlineQuery(update.getInlineQuery());
            } catch (TelegramApiException | IOException e) {
                System.out.println(e);;
            }
        }
        if (message.hasLocation()) {
            try {
                handleLocation(message);
            } catch (TelegramApiException | IOException e) {
                System.out.println(e);;
            }
        }
        if (message.hasEntities()) {
            try {
                handleCommand(message);
            } catch (TelegramApiException | IOException e) {
                System.out.println(e);;
            }
        }

    }

    private void handleInlineQuery(InlineQuery inlineQuery) throws TelegramApiException, IOException {
        List<InlineQueryResult> inlineQueryResults = new ArrayList<>();

        if (inlineQuery.getLocation() != null) {
            String weatherInformation = getWeather(inlineQuery.getLocation());
            InputMessageContent m1 = InputTextMessageContent.builder().messageText(weatherInformation).build();
            inlineQueryResults.add(InlineQueryResultArticle.builder().title("Погода \uD83C\uDF1E").id("1").inputMessageContent(m1).build());
        }

        InputMessageContent m2 = InputTextMessageContent.builder().messageText(BitcoinInfo.getCurrencyInfo()).build();
        InputMessageContent m3 = InputTextMessageContent.builder().messageText("Соси \uD83C\uDF46").build();
        InputMessageContent m4 = InputTextMessageContent.builder().messageText("[Открыть котека в браузере](" + Photos.getRandomCatUrl() + ")").parseMode("MarkdownV2").build();
        InputMessageContent m5 = InputTextMessageContent.builder().messageText("" + ((int) (Math.random() * 100))).build();
        inlineQueryResults.add(InlineQueryResultArticle.builder().title("Курс биткоина \uD83D\uDCB0").id("2").inputMessageContent(m2).build());
        inlineQueryResults.add(InlineQueryResultArticle.builder().title("Пожелать здоровья \uD83D\uDE07").id("3").inputMessageContent(m3).build());
        inlineQueryResults.add(InlineQueryResultArticle.builder().title("Послать котека \uD83D\uDC31").id("4").inputMessageContent(m4).build());
        inlineQueryResults.add(InlineQueryResultArticle.builder().title("Число 0-100 \uD83D\uDD22").id("5").inputMessageContent(m5).build());
        AnswerInlineQuery answerInlineQuery = AnswerInlineQuery.builder().inlineQueryId(inlineQuery.getId()).results(inlineQueryResults).cacheTime(0).build();
        execute(answerInlineQuery);
    }

    private void handleLocation(Message message) throws TelegramApiException, IOException {

        execute(SendMessage.builder().chatId(message.getChatId().toString()).text(getWeather(message.getLocation())).build());
    }

    private void handleCommand(Message message) throws TelegramApiException, IOException {
        String s = message.getEntities().get(0).getText();

        switch (s) {
            case "/start" :
                String greeting = "Привет! С помощью этого бота ты сможешь узнать погоду и получить фото котека! \n" +
                "Также введя в окне чата @TsavaphBot ты сможешь отправить информацию о своей погоде, узнать курс биткоина к доллару, получить рандомное число и пожелать здоровья другу!";
                execute(SendMessage.builder().chatId(message.getChatId().toString()).text(greeting).build());
                break;

            case "/help" :
                String help = "Описание команд:\n" +
                        "/cat - Получить рандомную фотку котека\n" +
                        "/weather - Получить кнопку \"Отправить местоположение\" для получения информации о погоде\n\n" +
                        "Дополнительно вводя в окне чата \"@TsavaphBot \" ты можешь отправить информацию о своей погоде, узнать курс биткоина к доллару, пожелать здоровья другу, послать фотографию котека и отправить рандомное число от 0 до 100\n\n" +
                        "P.S. На данный момент Телеграм позволяет узнать информацию о погоде только со смартфона :(\n" +
                        "P.S.S Разреши делиться местоположением\n";
                execute(SendMessage.builder().chatId(message.getChatId().toString()).text(help).build());

            case "/cat" :
                execute(SendPhoto.builder().chatId(message.getChatId().toString()).photo(new InputFile(Photos.getRandomCatUrl())).build());
                break;

            case "/weather" :
                SendMessage msg = new SendMessage();
                msg.setChatId(message.getChatId().toString());
                msg.setText("Отправьте свое местоположение, чтобы получить информацию о погоде");
                ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
                KeyboardButton button1 = KeyboardButton.builder().text("Поделиться местоположением").requestLocation(true).build();
                List<KeyboardButton> buttons = Arrays.asList(button1);
                KeyboardRow row = new KeyboardRow(buttons);
                List<KeyboardRow> keyboard = new ArrayList<>();
                keyboard.add(row);
                keyboardMarkup.setKeyboard(keyboard);
                keyboardMarkup.setSelective(true);
                keyboardMarkup.setOneTimeKeyboard(true);
                msg.setReplyMarkup(keyboardMarkup);

                execute(msg);
                break;
        default :
            execute(SendMessage.builder().chatId(message.getChatId().toString()).text("Такой команды ботом не предусмотрено").build());
        }
    }

    private String getWeather(Location location) throws IOException {
        Weather weather = new Weather(location.getLatitude(), location.getLongitude(), xYandexApiKey);
        return weather.getWeatherInformation();
    }
    
}