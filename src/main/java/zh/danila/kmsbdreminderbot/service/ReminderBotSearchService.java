package zh.danila.kmsbdreminderbot.service;

import lombok.RequiredArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import zh.danila.kmsbdreminderbot.entity.ReminderEntity;
import zh.danila.kmsbdreminderbot.repository.ReminderBotRepository;
import zh.danila.kmsbdreminderbot.util.DateUtils;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.stream.Collectors;

import static zh.danila.kmsbdreminderbot.config.Messages.LIST_ROW;
import static zh.danila.kmsbdreminderbot.service.ReminderBotCrudService.BD_FORMATTER;

@Service
@RequiredArgsConstructor
public class ReminderBotSearchService {
    private static final Comparator<ReminderEntity> BD_COMPARATOR = (o1, o2) -> {
        int monthCompare = Integer.compare(o1.getBirthdate().getMonthValue(), o2.getBirthdate().getMonthValue());
        if (monthCompare == 0) {
            return Integer.compare(o1.getBirthdate().getDayOfMonth(), o2.getBirthdate().getDayOfMonth());
        } else {
            return monthCompare;
        }
    };

    private final ReminderBotRepository repository;

    public String list(Long chatId) {
        var reminders = repository.findReminderEntitiesByReminderId_ChatId(chatId);
        LocalDate now = LocalDate.now();
        return reminders.stream().sorted(BD_COMPARATOR)
                .map(r -> String.format(LIST_ROW, BD_FORMATTER.format(r.getBirthdate()), r.getReminderId().getUsername()))
                .collect(Collectors.joining("\n"));
    }

    public String nearest(Long chatId) {
        var reminders = repository.findReminderEntitiesByReminderId_ChatId(chatId);
        LocalDate now = LocalDate.now();
        return reminders.stream().sorted(BD_COMPARATOR)
                .filter(r -> DateUtils.isAfterToday(r.getBirthdate()))
                .limit(5)
                .map(r -> String.format(LIST_ROW, BD_FORMATTER.format(r.getBirthdate()), r.getReminderId().getUsername()))
                .collect(Collectors.joining("\n"));
    }
}
