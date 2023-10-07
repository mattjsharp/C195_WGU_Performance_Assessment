package C195.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility class containing 2 static methods to format LocalDateTimes.
 * 
 * @author mattjsharp
 */
public abstract class QuickDateTimeFormatter {
    /**
     * 
     * @param date The provided date.
     * @return A String containing a formatted 12-hour time.
     */
    public static final String quickTimeFormater(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("h:mm a");
        return date.format(formatter);
    }
    
    /**
     * 
     * @param date The provided date.
     * @return A String containing a formatted date showing the day and month.
     */
    public static final String quickDateFormatter(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMM");
        return date.format(formatter);
    }
}
