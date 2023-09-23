package C195.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author LabUser
 */
public abstract class TableDateTimeFormatter {
    public static String tableTimeFormater(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return date.format(formatter);
    }
    
    public static String tableDateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMMM d");
        return date.format(formatter);
    }
}
