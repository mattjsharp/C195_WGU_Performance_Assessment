/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.dao.AppointmentQuery;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.TableView;

/**
 *
 * @author LabUser
 */
public class MainController extends Controller implements AppointmentQuery {

    @FXML
    TableView appointmentTable;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            getAppointments();
        } catch (SQLException ex) {
            ex.getMessage();
        }
    }
    
}
