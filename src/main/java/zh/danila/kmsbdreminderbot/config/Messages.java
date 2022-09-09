package zh.danila.kmsbdreminderbot.config;

import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Messages {
    public static final String CONGRAT_MSG = "Сегодня днюха у %s! Поздравьте засранца\uD83C\uDF89\uD83E\uDD73";
    public static final String DATE_FORMAT_MSG = "Используй дату в формате dd-mm-yyyy плз";
    public static final String REMINDER_ALREADY_EXIST = "Напоминалка для %s уже задана";
    public static final String REMINDER_SET = "Напоминалка для %s установлена";
    public static final String REMINDER_NOT_FOUND = "Напоминалка не найдена";
    public static final String REMINDER_REMOVED = "Напоминалка для %s удалена";
    public static final String BROKEN_MSG = "Что-то поломалось, как обычно зовите Даню";

    public static final String LIST_ROW = "%s днюха у %s";
    public static final String COMMANDS_MSG = "Доступные команды:\n" +
            "/add {username} {birthdate ДД-ММ-ГГГГ}\n" +
            "/remove {username}\n" +
            "/nearest\n" +
            "/list\n" +
            "/help";


    public static List<String> rudes = List.of("Руки из жопы вынь",
            "Глаза разуй, чурчхела",
            "Голову дома забыл, да?",
            "Ну ты прям Евлампий");



    public static String getValidationMsg() {
        int i = ThreadLocalRandom.current().nextInt(rudes.size());
        return rudes.get(i);
    }
}
