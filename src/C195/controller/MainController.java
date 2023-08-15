/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.dao.AppointmentQuery;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

/**
 *
 * @author LabUser
 */
public class MainController extends Controller implements AppointmentQuery {

    @FXML
    TableView appointmentTable;
    
    @FXML
    TableColumn appointmentTitleColumn, 
            appointmentDescriptionColumn, 
            appointmentLocationColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //appointmentTable.setItems();
        //appointmentTitleColumn.setCellFactory(new PropertyValueFactory<>(""));
        
        List<Appointment> appointments = 
        appointmentTable.getItems().addAll();
    }
    
}
