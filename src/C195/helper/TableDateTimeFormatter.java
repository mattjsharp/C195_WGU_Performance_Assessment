package C195.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class containing 2 static methods to format LocalDateTimes.
 * @author LabUser
 */
public abstract class TableDateTimeFormatter {
    public static final String tableTimeFormater(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return date.format(formatter);
    }
    
    public static final String tableDateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        return date.format(formatter);
    }
}
