package zh.danila.kmsbdreminderbot.entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import java.io.Serializable;

@Embeddable
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Builder
public class ReminderId implements Serializable {
    @Column
    private String username;
    @Column(name = "chat_id")
    private Long chatId;
}
