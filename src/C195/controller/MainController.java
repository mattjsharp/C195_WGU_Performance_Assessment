/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class MainController extends Controller {
    
    @FXML
    Tab appointmentsTab;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            appointmentsTab.setContent(FXMLLoader.load(getClass().getResource("../view/AppointmentTab.fxml")));
        } catch (IOException e) {
            e.getMessage();
        }
        appointmentsTab.setText(l10n.getString("appointments"));
    }    
    
}
