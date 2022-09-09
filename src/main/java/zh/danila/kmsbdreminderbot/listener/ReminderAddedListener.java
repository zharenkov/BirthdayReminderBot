package zh.danila.kmsbdreminderbot.listener;

import lombok.RequiredArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import zh.danila.kmsbdreminderbot.event.ReminderAddedEvent;
import zh.danila.kmsbdreminderbot.service.ReminderBotSender;
import zh.danila.kmsbdreminderbot.util.DateUtils;

import static zh.danila.kmsbdreminderbot.config.Messages.CONGRAT_MSG;

@Component
@RequiredArgsConstructor
public class ReminderAddedListener {

    private final ReminderBotSender sender;

    @Async
    @EventListener(ReminderAddedEvent.class)
    public void onMessage(ReminderAddedEvent event) throws InterruptedException {
        var reminderEntity = event.getReminderEntity();
        if (DateUtils.isToday(reminderEntity.getBirthdate())) {
            Thread.sleep(2000);
            var chatId = reminderEntity.getReminderId().getChatId();
            var msg = String.format(CONGRAT_MSG, reminderEntity.getReminderId().getUsername());
            sender.sendMessage(chatId, msg);
        }
    }
}
