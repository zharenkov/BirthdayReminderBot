package zh.danila.kmsbdreminderbot.util;

import lombok.experimental.UtilityClass;

import java.time.LocalDate;

@UtilityClass
public class DateUtils {

    public static boolean isToday(LocalDate date) {
        var now = LocalDate.now();
        return now.getDayOfMonth() == date.getDayOfMonth() &&
                now.getMonthValue() == date.getMonthValue();
    }

    public static boolean isAfterToday(LocalDate date) {
        var now = LocalDate.now();
        return now.getMonthValue() <= date.getMonthValue()
                && now.getDayOfMonth() <= date.getDayOfMonth();
    }
}
