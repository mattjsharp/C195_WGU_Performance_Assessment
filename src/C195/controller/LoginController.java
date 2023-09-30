package C195.controller;

import C195.dao.LoginQuery;
import C195.helper.LoginActivityLogger;
import C195.model.User;
import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.time.ZoneId;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import C195.dao.UserDbActions;

/**
 *
 * @author LabUser
 */
public class LoginController extends Controller implements LoginQuery, UserDbActions {

    @FXML
    TextField usernameField;
    
    @FXML
    PasswordField passwordField;

    @FXML
    Label greetingLabel, zoneLabel, errorLabel;
    
    @FXML
    Button loginButton;
    
    LoginActivityLogger logger = LoginActivityLogger.getInstance();

    /**
     *
     * @param e
     * @throws IOException
     * @throws java.sql.SQLException
     */
    public void submit(ActionEvent e) throws IOException, SQLException {
        
        String username = usernameField.getText().trim(),
                password = passwordField.getText().trim(),
                errorString = "";

        boolean valid = true;

        if (username.isEmpty() || password.isEmpty()) {
            errorString = errorString + (valid ? "" : "\n") + l10n.getString("err1");
            valid = false;
            logger.newLog("LOGIN FAIL: empty username and/or passord field(s)");
        }
        User user = login(username, password);
        if (user == null) {
            errorString = errorString + (valid ? "" : "\n") + l10n.getString("err2");
            valid = false;
            logger.newLog("LOGIN FAIL: credentials not found for \"" + username + "\"");
        }

        errorLabel.setManaged(!valid);
        errorLabel.setText(errorString);

        if (valid) {
            User.setUser(user);
            logger.newLog("LOGIN SUCCESS: \"" + username + "\" logged in successfully");
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Main.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        greetingLabel.setText(l10n.getString("welcome"));
        usernameField.setPromptText(l10n.getString("username"));
        passwordField.setPromptText(l10n.getString("password"));
        loginButton.setText(l10n.getString("login"));
        zoneLabel.setText(String.valueOf(ZoneId.systemDefault()));
        
        errorLabel.setManaged(false);
    }

}
