package zh.danila.kmsbdreminderbot.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import zh.danila.kmsbdreminderbot.entity.ReminderEntity;
import zh.danila.kmsbdreminderbot.entity.ReminderId;

import java.util.List;

@Repository
public interface ReminderBotRepository extends CrudRepository<ReminderEntity, ReminderId> {

    List<ReminderEntity> findReminderEntitiesByReminderId_ChatId(Long chatId);
}
