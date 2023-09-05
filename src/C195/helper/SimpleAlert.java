
package C195.helper;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

/**
 *
 * @author LabUser
 */
public final class SimpleAlert {
    private static Alert alert;
    
    private SimpleAlert() {}
    
    public static void simpleAlert(String title, String content) {
        alert = new Alert(AlertType.WARNING);
        alert.setTitle(title);
        alert.setContentText(content);
        alert.show();
    }
}
