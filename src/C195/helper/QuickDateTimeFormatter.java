package C195.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class containing 2 static methods to format LocalDateTimes.
 * @author LabUser
 */
public abstract class QuickDateTimeFormatter {
    public static final String quickTimeFormater(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return date.format(formatter);
    }
    
    public static final String quickDateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        return date.format(formatter);
    }
}
