package zh.danila.kmsbdreminderbot.controller;

import com.github.kshashov.telegram.api.MessageType;
import com.github.kshashov.telegram.api.TelegramMvcController;
import com.github.kshashov.telegram.api.bind.annotation.BotController;
import com.github.kshashov.telegram.api.bind.annotation.BotPathVariable;
import com.github.kshashov.telegram.api.bind.annotation.BotRequest;
import com.github.kshashov.telegram.api.bind.annotation.request.MessageRequest;
import com.pengrad.telegrambot.model.Chat;
import lombok.RequiredArgsConstructor;
import zh.danila.kmsbdreminderbot.config.ReminderBotConfiguration;
import zh.danila.kmsbdreminderbot.service.ReminderBotCrudService;
import zh.danila.kmsbdreminderbot.service.ReminderBotSearchService;

import static zh.danila.kmsbdreminderbot.config.Messages.*;

@RequiredArgsConstructor
@BotController
public class ReminderBotController implements TelegramMvcController {

    @Override
    public String getToken() {
        return config.getToken();
    }

    private final ReminderBotCrudService reminderBotCrudService;
    private final ReminderBotSearchService reminderBotSearchService;
    private final ReminderBotConfiguration config;


    @BotRequest(value = "/start")
    public String start() {
        return COMMANDS_MSG;
    }

    @BotRequest(value = {"/help", "/help@Kms_bd_reminder_bot"})
    public String help() {
        return COMMANDS_MSG;
    }

    @BotRequest(value = {"/nearest", "/nearest@Kms_bd_reminder_bot"})
    public String nearest(Chat chat) {
        return reminderBotSearchService.nearest(chat.id());
    }

    @BotRequest(value = {"/list","/list@Kms_bd_reminder_bot"})
    public String list(Chat chat) {
        return reminderBotSearchService.list(chat.id());
    }


    @MessageRequest(value = {"/add {username} {birthdate}","/add@Kms_bd_reminder_bot {username} {birthdate}"})
    public String addUser(@BotPathVariable("username") String username,
                           @BotPathVariable("birthdate") String birthdate,
                           Chat chat) {
        String respMsg = reminderBotCrudService.save(username, birthdate, chat.id());
        return respMsg;
    }

    @MessageRequest(value = {"/add", "/add@Kms_bd_reminder_bot", "/remove",
            "/remove@Kms_bd_reminder_bot",
            "/add **", "/add@Kms_bd_reminder_bot **"})
    public String hands() {
        return getValidationMsg();
    }

    @MessageRequest(value = {"/remove {username}", "/remove@Kms_bd_reminder_bot {username}"})
    public String removeUser(@BotPathVariable("username") String username, Chat chat) {
        String respMsg = reminderBotCrudService.remove(username, chat.id());
        return respMsg;
    }


}
