package zh.danila.kmsbdreminderbot.config;

import com.github.kshashov.telegram.config.TelegramBotGlobalProperties;
import com.github.kshashov.telegram.config.TelegramBotGlobalPropertiesConfiguration;
import com.pengrad.telegrambot.TelegramBot;
import lombok.Getter;
import okhttp3.OkHttpClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Component
public class ReminderBotConfiguration implements TelegramBotGlobalPropertiesConfiguration {

    @Getter
    @Value("${bot.token}")
    private String token;

    @Getter
    private TelegramBot bot;


    @Override
    public void configure(TelegramBotGlobalProperties.Builder builder) {
        OkHttpClient okHttp = new OkHttpClient.Builder()
                .connectTimeout(12, TimeUnit.SECONDS)
                .build();
        builder
                .configureBot(token, botBuilder -> {
                    botBuilder.configure(builder1 -> builder1.okHttpClient(okHttp));
                })
                .processBot(token, bot -> this.bot = bot);
    }
}
