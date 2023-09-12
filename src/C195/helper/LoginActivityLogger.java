package C195.helper;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;

/**
 *
 * @author LabUser
 */
public final class LoginActivityLogger {

    private static LoginActivityLogger instance;
    private PrintWriter writer;

    private LoginActivityLogger() {
        try {
            String logFilePath = "login_activity.txt";
            writer = new PrintWriter(new FileWriter(logFilePath, true), true);
        } catch (IOException e) {
            e.getMessage();
        }
    }

    public static LoginActivityLogger getInstance() {
        if (instance == null) {
            instance = new LoginActivityLogger();
        }

        return instance;
    }
    
    public void newLog(String message) {
        String formattedMessage = "[" + LocalDateTime.now() + "] - " + message;
        writer.println(formattedMessage);
    }
}