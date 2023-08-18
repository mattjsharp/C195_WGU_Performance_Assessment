/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.model.Appointment;
import C195.dao.AppointmentQuery;
import C195.helper.DateFormatter;
import java.net.URL;
import java.time.LocalDateTime;
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

    @Override
    public void initialize(URL url, ResourceBundle rb) {
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

        List<Appointment> appointments = getAppointments();
        appointmentTable.getItems().addAll(appointments);
    }

}
