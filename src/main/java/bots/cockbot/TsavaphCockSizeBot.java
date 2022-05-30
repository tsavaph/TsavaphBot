package bots.cockbot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.AnswerInlineQuery;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.inlinequery.InlineQuery;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.inputmessagecontent.InputTextMessageContent;
import org.telegram.telegrambots.meta.api.objects.inlinequery.result.*;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;


public class TsavaphCockSizeBot extends TelegramLongPollingBot {

    private String botUserName;
    private String botToken;
    private final static String[] OPINION_ARRAY = {"a juicy", "an ugly", "a beautiful", "a vomitous", "a gorgeous", "a stinky", "an amazing"};
    private final static String[] SIZE_ARRAY = {"tiny","little","small","average","normal-sized","huge"};
    private final static String[] AGE_ARRAY = {" old ", " "};
    private final static String[] SHAPE_ARRAY = {"curved", "straight", "conical", "unusual", "barrel-shaped"};
    private final static String[] COLOR_ARRAY = {"white", "black", "green", "brown", "red", "blue", "yellow", "violet", "gold"};


    @Override
    public String getBotUsername() {
        return botUserName;
    }

    @Override
    public String getBotToken() {
        return botToken;
    }

    public TsavaphCockSizeBot(String botUserName, String botToken) {
        this.botUserName = botUserName;
        this.botToken = botToken;
    }

    @Override
    public void onUpdateReceived(Update update) {

        if (update.hasInlineQuery()) {
            try {
                handleInlineQuery(update.getInlineQuery());
            } catch (TelegramApiException | IOException e) {
                System.out.println(e);
            }
        }
    }

    private void handleInlineQuery(InlineQuery inlineQuery) throws TelegramApiException, IOException {
        int cockSize;

        cockSize = (int) (Math.random() * 25);

        List<InlineQueryResult> inlineQueryResults = new ArrayList<>();

        InputMessageContent m1 = InputTextMessageContent.
                builder().
                messageText("Today I have " + cockDescriber(cockSize)).
                parseMode("MarkdownV2").build();

        inlineQueryResults.add(InlineQueryResultArticle.builder().
                title("Send your cock information").
                id("1").
                inputMessageContent(m1).build());

        AnswerInlineQuery answerInlineQuery = AnswerInlineQuery.
                builder().
                inlineQueryId(inlineQuery.getId()).
                results(inlineQueryResults).
                cacheTime(43200).
                isPersonal(true).
                build();

        execute(answerInlineQuery);
    }

    private String cockDescriber(int cockSize) {
        String opinion;
        String size;
        String age;
        String shape;
        String color;
        String emoji;

        if (cockSize < 5) {
            emoji = "\uD83D\uDE22";
            size = SIZE_ARRAY[0];
        }
        else if (cockSize >= 5 && cockSize < 8) {
            emoji = "\uD83D\uDE41";
            size = SIZE_ARRAY[1];
        }
        else if (cockSize >= 8 && cockSize < 12) {
            emoji = "\uD83D\uDE15";
            size = SIZE_ARRAY[2];
        }
        else if (cockSize >= 12 && cockSize < 16) {
            emoji = "\uD83D\uDE10";
            size = SIZE_ARRAY[3];
        }
        else if (cockSize >= 16 && cockSize < 20) {
            emoji = "\uD83D\uDE0F";
            size = SIZE_ARRAY[4];
        }
        else {
            emoji = "\uD83E\uDD74";
            size = SIZE_ARRAY[5];
        }

        opinion = OPINION_ARRAY[(int) (Math.random() * 6.99)];
        age = AGE_ARRAY[(int) (Math.random() * 1.999)];
        shape = SHAPE_ARRAY[(int) (Math.random() * 4.99)];
        color = COLOR_ARRAY[(int) (Math.random() * 8.99)];

        return "*" + opinion + " "
                +  size + " "
                + cockSize + " cm"
                + age
                + shape + " "
                + color + " cock* "
                + emoji;
    }
}