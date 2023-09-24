package C195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 * Utility class to provide relatively simple alerts accessible through a single method.
 * @author LabUser
 */
public final class SimpleAlert {

    private static Alert alert;

    private SimpleAlert() {
    }

    public static final void simpleWarning(String title, String content) {
        alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    
    public static final void simpleInformation(String title, String content) {
        alert = new Alert(AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
    
    public static final void simpleError(String title, String content) {
        alert = new Alert(AlertType.ERROR);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }

    public static final boolean simpleConfirm(String title, String content) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        boolean confirmed = false;
        alert.setTitle(title);
        alert.setHeaderText(content);
        ButtonType response = alert.showAndWait().get();
        if (response == ButtonType.OK) confirmed = true;
        return confirmed;
    }
}
