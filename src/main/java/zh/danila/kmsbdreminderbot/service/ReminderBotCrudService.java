package zh.danila.kmsbdreminderbot.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import zh.danila.kmsbdreminderbot.entity.ReminderEntity;
import zh.danila.kmsbdreminderbot.entity.ReminderId;
import zh.danila.kmsbdreminderbot.event.ReminderAddedEvent;
import zh.danila.kmsbdreminderbot.repository.ReminderBotRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Comparator;
import java.util.stream.Collectors;

import static zh.danila.kmsbdreminderbot.config.Messages.*;

@Service
@RequiredArgsConstructor
@Slf4j
public class ReminderBotCrudService {
    static final DateTimeFormatter BD_FORMATTER = DateTimeFormatter.ofPattern("dd-MM-yyyy");

    private final ApplicationEventPublisher eventPublisher;
    private final ReminderBotRepository repository;

    public String save(String username, String birthdate, Long chatId) {
        LocalDate date;
        if (StringUtils.isEmpty(username)) return getValidationMsg();
        try {
            date = LocalDate.parse(birthdate, BD_FORMATTER);
        } catch (DateTimeParseException ex) {
            log.error("Failed to parse date {}", birthdate, ex);
            return DATE_FORMAT_MSG;
        }
        if (repository.findById(ReminderId.builder().chatId(chatId).username(username).build()).isPresent()) {
            return String.format(REMINDER_ALREADY_EXIST, username);
        }
        try {
            var entity = ReminderEntity.builder()
                    .reminderId(ReminderId.builder().username(username).chatId(chatId).build())
                    .birthdate(date)
                    .build();
            entity = repository.save(entity);
            eventPublisher.publishEvent(new ReminderAddedEvent(this, entity));
            return String.format(REMINDER_SET, username);
        } catch (Exception ex) {
            log.error("Failed to save reminder", ex);
            return BROKEN_MSG;
        }
    }

    public String remove(String username, Long chatId) {
        var id = ReminderId.builder().chatId(chatId).username(username).build();
        if (repository.findById(id).isPresent()) {
            repository.deleteById(id);
            return String.format(REMINDER_REMOVED, username);
        } else {
            return String.format(REMINDER_NOT_FOUND, username);
        }
    }
}
