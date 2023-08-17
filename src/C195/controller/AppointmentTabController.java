/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.model.Appointment;
import C195.dao.AppointmentQuery;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author LabUser
 */
public class AppointmentTabController extends Controller implements AppointmentQuery {

    @FXML
    TableView<Appointment> appointmentTable;
    
    @FXML
    TableColumn<Appointment, Integer> appointmentIdColumn;
    
    @FXML
    TableColumn<Appointment, String> appointmentTitleColumn;
    
    @FXML
    TableColumn<Appointment, String> appointmentDescriptionColumn;
    
    @FXML
    TableColumn<Appointment, String> appointmentLocationColumn;
    
    @FXML
    TableColumn<Appointment, Integer> customerIdColumn;
    
    @FXML
    TableColumn<Appointment, Integer> userIdColumn;
    
    @FXML
    TableColumn<Appointment, Integer> contactIdColumn;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        userIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));
        contactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        
        appointmentIdColumn.setText(l10n.getString("appointmentId"));
        appointmentTitleColumn.setText(l10n.getString("title"));
        appointmentDescriptionColumn.setText(l10n.getString("description"));
        appointmentLocationColumn.setText(l10n.getString("location"));
        customerIdColumn.setText(l10n.getString("customerId"));
        userIdColumn.setText(l10n.getString("userId"));
        contactIdColumn.setText(l10n.getString("contactId"));
        
        List<Appointment> appointments = getAppointments();
        appointmentTable.getItems().addAll(appointments);
    }
    
}
