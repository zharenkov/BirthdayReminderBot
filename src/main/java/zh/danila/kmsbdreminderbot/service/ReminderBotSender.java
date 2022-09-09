package zh.danila.kmsbdreminderbot.service;

import com.pengrad.telegrambot.TelegramBot;
import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import zh.danila.kmsbdreminderbot.config.ReminderBotConfiguration;

@Component
@RequiredArgsConstructor
public class ReminderBotSender {

    private final ReminderBotConfiguration reminderBotConfiguration;
    private TelegramBot bot;

    public void sendMessage(Long chatId, String message) {
        reminderBotConfiguration.getBot().execute(new SendMessage(chatId, message));
    }

}
