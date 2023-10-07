package C195.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * Singleton class to log activities that occur on the login in screen.
 * 
 * @author mattjsharp
 */
public final class LoginActivityLogger {

    private static LoginActivityLogger instance;
    private PrintWriter writer;

    /**
     * Private constructor for the singleton class.
     * Only runs when there is no logger created.
     */
    private LoginActivityLogger() {
        try {
            String logFilePath = "login_activity.txt";
            writer = new PrintWriter(new FileWriter(logFilePath, true), true);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    /**
     * 
     * @return The current instance.
     */
    public static LoginActivityLogger getInstance() {
        if (instance == null) {
            instance = new LoginActivityLogger();
        }

        return instance;
    }
    
    /**
     * Appends the login_activity document with a new record.
     * 
     * @param message The message to be logged.
     */
    public void newLog(String message) {
        String formattedMessage = "[" + LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime() + "] - " + message;
        writer.println(formattedMessage);
    }
}