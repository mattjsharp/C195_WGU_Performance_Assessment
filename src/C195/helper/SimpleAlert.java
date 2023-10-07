package C195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Utility class to provide relatively simple alerts accessible through a single method.
 * 
 * @author mattjsharp
 */
public final class SimpleAlert {

    private static Alert alert;

    /**
     * Displays a warning prompt based off of user input.
     * 
     * @param title The title of the message.
     * @param content The content of the message.
     */
    public static final void simpleWarning(String title, String content) {
        alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    
    /**
     * Displays a prompt containing information based off of user input.
     * 
     * @param title The title of the message.
     * @param content The content of the message.
     */
    public static final void simpleInformation(String title, String content) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    
    /**
     * Props the user of an error that occurred based on user input.
     * 
     * @param title The title of the message.
     * @param content The content of the message.
     */
    public static final void simpleError(String title, String content) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    /**
     * Opens a dialog asking a user for confirmation.
     * 
     * @param title The title of the message.
     * @param content The content of the message.
     * @return A boolean value if the user confirms the dialog.
     */
    public static final boolean simpleConfirm(String title, String content) {
        alert = new Alert(Alert.AlertType.CONFIRMATION);
        boolean confirmed = false;
        alert.setTitle(title);
        alert.setHeaderText(content);
        ButtonType response = alert.showAndWait().get();
        if (response == ButtonType.OK) confirmed = true;
        return confirmed;
    }
}
