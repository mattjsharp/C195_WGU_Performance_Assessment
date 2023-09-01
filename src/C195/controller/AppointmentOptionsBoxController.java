/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package C195.controller;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class AppointmentOptionsBoxController extends Controller {
    
    @FXML
    Button modifyAppointmentButton, deleteAppointmentButton;
    
    
    
    boolean selected;

    public void addAppointment(ActionEvent event) {
        
    }
    
    public void modifyAppointment(ActionEvent event) {
        System.out.println("Modify");
    }
    
    public void deleteAppointment(ActionEvent event) {
        System.out.println("Delete");
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        
        if(!selected) {
            modifyAppointmentButton.setDisable(true);
            deleteAppointmentButton.setDisable(true);
        }
    }    
    
}
