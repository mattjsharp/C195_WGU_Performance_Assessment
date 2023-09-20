package C195.controller;

import C195.model.Appointment;
import C195.dao.AppointmentQuery;
import C195.dao.DeleteAppointment;
import C195.helper.SimpleAlert;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

/**
 *
 * @author LabUser
 */
public class AppointmentTabController extends Controller implements AppointmentQuery, DeleteAppointment {

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
    TableColumn<LocalDateTime, String> appointmentStartColumn;

    @FXML
    TableColumn<LocalDateTime, String> appointmentEndColumn;

    @FXML
    TableColumn<Appointment, Integer> appointmentCustomerIdColumn;

    @FXML
    TableColumn<Appointment, Integer> appointmentUserIdColumn;

    @FXML
    Button modifyAppointmentButton, deleteAppointmentButton;

    @FXML
    ToggleGroup appointmentSort;

    /**
     *  Initializes the controller class.
     * 
     * @param url
     * @param rb
     */
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

        // Updating the table.
        updateTable();

        // Checkin for near appointments
        checkAppointments();

        // Adding and event listener to the group of radio buttons.
        appointmentSort.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selectedRadioButton = (RadioButton) newVal;
                String selectedValue = selectedRadioButton.getText();
                System.out.println("Selected: " + selectedValue);
            }
        });
        
        // Setting a place holder in case there are no appointments schedueled
        appointmentTable.setPlaceholder(new javafx.scene.control.Label("There are no Appointments Scheduled"));
    }

    /**
     * Updates the appointment table.
     * Queries the appointment table from the client_schedule database and stores each record a list.
     * Clears the current table replaces the records with the new List.
     */
    private void updateTable() {
        List<Appointment> appointments = getAppointments();
        appointmentTable.getItems().clear();
        appointmentTable.getItems().addAll(appointments);
    }

    /**
     * Adds a new appointment.
     * Opens a dialog with several fields to allow users to add new appointments.
     * 
     * @throws IOException Throws an IOException if there is an issue with the FXML document.
     */
    public void addAppointment() throws IOException {

        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppointmentDialog.fxml"));
        Parent root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Add Appointment");

        Controller controller = loader.getController();
        controller.setStage(dialogStage);

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        updateTable();
    }
    
    /**
     * Modifies a selected appointment.
     * Opens a modified appointment dialog with the appointment details loaded in each of the fields.
     * Notifies the user if the no appointment is selected.
     * 
     * @throws IOException Throws an IOException if there is an issue with the FXML document.
     */
    public void modifyAppointment() throws IOException {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            SimpleAlert.simpleWarning("No appointment Selected", "No appointment Selected");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppointmentDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Modify Appointment");

            AppointmentDialogController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setAppointment(selectedAppointment);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();

            updateTable();
        }

    }

    /**
     * Deletes a selected appointment.
     * Asks for confirmation window after a user presses the delete button to delete the appointment selected on the appointment table.
     * Displays a warning if there is no appointment selected and notifies the user if the delete was successful or not.
     */
    public void deleteAppointment() {
        Appointment selectedAppointment = appointmentTable.getSelectionModel().getSelectedItem();

        if (selectedAppointment == null) {
            SimpleAlert.simpleWarning("No appointment Selected", "No appointment Selected");
        } else {
            if (SimpleAlert.simpleConfirm("Confirmation", "Are you sure you want to delete this appointment?")) {
                if (deleteAppointment(selectedAppointment.getId())) {
                    updateTable();
                    SimpleAlert.simpleInformation("Delete Success", "ID: " + selectedAppointment.getId() + " TYPE: " + selectedAppointment.getType() + " deleted successfully.");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            }
        }
    }

    /**
     * Checks if there are any appointments within 15 minutes.
     * Queries the appointment table and checks each appointment if it meets the time criteria.
     * Displays a message displaying either upcoming appointments or that none are near.
     */
    private void checkAppointments() {
        int appointmentCount = 0;
        String appointmentDescription = "";
        List<Appointment> appointments = getAppointments();
        for (Appointment appointment : appointments) {
            int minutesDifference = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), appointment.getStartDate());
            if (minutesDifference <= 15 && minutesDifference > 0) {
                appointmentDescription += "ID: " + appointment.getId() + "\tSTART: " + appointment.getFormattedStartDate() + "\tTITLE: " + appointment.getTitle() + "\n\n";
                appointmentCount++;
            }
        }

        if (appointmentCount > 0) {
            SimpleAlert.simpleWarning("" + appointmentCount + " Near Appointments", "There are " + appointmentCount + " appointments within the near future:\n\n" + appointmentDescription);
        } else {
            SimpleAlert.simpleInformation("No Appointments", "There are no appointments scheduded in the near future.");
        }
    }
}
