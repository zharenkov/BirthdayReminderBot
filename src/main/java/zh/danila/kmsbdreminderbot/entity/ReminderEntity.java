package zh.danila.kmsbdreminderbot.entity;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name="REMINDER_TAB")
public class ReminderEntity {

    @EmbeddedId
    private ReminderId reminderId;
    @Column(name = "birth_date")
    private LocalDate birthdate;
}
