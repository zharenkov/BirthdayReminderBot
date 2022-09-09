package zh.danila.kmsbdreminderbot.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.SuperBuilder;
import org.springframework.context.ApplicationEvent;
import zh.danila.kmsbdreminderbot.entity.ReminderEntity;



public class ReminderAddedEvent extends ApplicationEvent {

    @Getter
    private ReminderEntity reminderEntity;

    public ReminderAddedEvent(Object source, ReminderEntity reminderEntity) {
        super(source);
        this.reminderEntity = reminderEntity;
    }
}
