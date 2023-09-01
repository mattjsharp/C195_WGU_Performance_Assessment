/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.model.Appointment;
import C195.dao.AppointmentQuery;
import C195.helper.DateFormatter;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 *
 * @author LabUser
 */
public class AppointmentTabController extends Controller implements AppointmentQuery {

    @FXML
    public TableView<Appointment> appointmentTable;

    @FXML
    TableColumn<Appointment, Integer> appointmentIdColumn;

    @FXML
    TableColumn<Appointment, String> appointmentTitleColumn;

    @FXML
    TableColumn<Appointment, String> appointmentDescriptionColumn;

    @FXML
    TableColumn<Appointment, String> appointmentLocationColumn;

    @FXML
    TableColumn<Appointment, Integer> appointmentContactIdColumn;

    @FXML
    TableColumn<Appointment, String> appointmentTypeColumn;

    @FXML
    TableColumn<Appointment, String> appointmentStartColumn;

    @FXML
    TableColumn<Appointment, String> appointmentEndColumn;

    @FXML
    TableColumn<Appointment, Integer> appointmentCustomerIdColumn;

    @FXML
    TableColumn<Appointment, Integer> appointmentUserIdColumn;

    @FXML
    Button modifyAppointmentButton, deleteAppointmentButton;

    @FXML
    ToggleGroup appointmentSort;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // Providing a propertyValueFactory for each column to control what is displayed
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        appointmentLocationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        appointmentContactIdColumn.setCellValueFactory(new PropertyValueFactory<>("contactId"));
        appointmentCustomerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("startDate"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endDate"));
        appointmentUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        // Setting the text for each of the column headers.
        appointmentIdColumn.setText(l10n.getString("appointmentId"));
        appointmentTitleColumn.setText(l10n.getString("title"));
        appointmentDescriptionColumn.setText(l10n.getString("description"));
        appointmentLocationColumn.setText(l10n.getString("location"));
        appointmentContactIdColumn.setText(l10n.getString("contactId"));
        appointmentTypeColumn.setText(l10n.getString("type"));
        appointmentStartColumn.setText(l10n.getString("start"));
        appointmentEndColumn.setText(l10n.getString("end"));
        appointmentCustomerIdColumn.setText(l10n.getString("customerId"));
        appointmentUserIdColumn.setText(l10n.getString("userId"));

        // Adding each appoinment from the results of the appointment query the a list.
        List<Appointment> appointments = getAppointments();
        
        // Populating the table with all of the appointments in the appointment list.
        appointmentTable.getItems().addAll(appointments);

        // Adding and event listener to the group of radio buttons.
        appointmentSort.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selectedRadioButton = (RadioButton) newVal;
                String selectedValue = selectedRadioButton.getText();
                System.out.println("Selected: " + selectedValue);
            }
        });

    }

    public void addAppointment(ActionEvent event) throws IOException {
        System.out.println("Add");
        dialog = new Dialog();
        dialog.setTitle("Add appointment");
        
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppointmentDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(loader.load());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        dialog.showAndWait();
        dialog.close();
    }

    public void modifyAppointment(ActionEvent event) {
        System.out.println("Modify");
    }

    public void deleteAppointment(ActionEvent event) {
        System.out.println("Delete");
    }

}
