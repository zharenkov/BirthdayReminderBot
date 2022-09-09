package zh.danila.kmsbdreminderbot.service;

import com.pengrad.telegrambot.request.SendMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import zh.danila.kmsbdreminderbot.config.ReminderBotConfiguration;
import zh.danila.kmsbdreminderbot.entity.ReminderEntity;
import zh.danila.kmsbdreminderbot.repository.ReminderBotRepository;

import java.time.LocalDate;
import java.util.ArrayList;

import static java.util.Optional.ofNullable;
import static zh.danila.kmsbdreminderbot.config.Messages.CONGRAT_MSG;

@Service
@RequiredArgsConstructor
public class ReminderBotScheduledService {

    private final ReminderBotRepository repository;
    private final ReminderBotSender sender;

    @Scheduled(cron = "0 0 10 * * *")
    public void sendReminder() {
        var today = LocalDate.now();
        var reminders = new ArrayList<ReminderEntity>();
        repository.findAll().forEach(reminders::add);

        reminders.stream()
                .filter(r -> today.getDayOfMonth() == r.getBirthdate().getDayOfMonth()
                        && today.getMonthValue() == r.getBirthdate().getMonthValue())
                .forEach(r ->
                    ofNullable(r.getReminderId()).ifPresent(rId ->
                           sender.sendMessage(rId.getChatId(), String.format(CONGRAT_MSG, rId.getUsername())))
                );
    }
}
