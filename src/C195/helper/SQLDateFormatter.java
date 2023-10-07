package C195.helper;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Utility Class for formatting dates for SQL statements.
 * 
 * @author mattjsharp
 */
public class SQLDateFormatter {
    /**
     * Accepts a LocalDateTime Object and returns a formatted String that can be used in SQL statements.
     * 
     * @param date The date to be set.
     * @return A formatted date to use in SQL statements.
     */
    public static final String formatDate(LocalDateTime date) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(formatter);
    }
}
