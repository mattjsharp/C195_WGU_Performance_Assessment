/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author LabUser
 */
public class LoginController implements Initializable {

    @FXML
    TextField usernameField, passwordField;

    @FXML
    Label errorLabel;

    /**
     *
     * @param e
     * @throws IOException
     */
    public void submit(ActionEvent e) throws IOException {
        String username = usernameField.getText(),
                password = passwordField.getText(),
                errorString = "";

        boolean valid = true;

        if (username.isEmpty() || password.isEmpty()) {
            errorString = errorString + (valid ? "" : "\n") + "username and password must not be empty";
            valid = false;
        }

        errorLabel.setManaged(!valid);
        errorLabel.setText(errorString);

        if (valid) {
            Stage stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Calendar.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        errorLabel.setManaged(false);
    }

}
