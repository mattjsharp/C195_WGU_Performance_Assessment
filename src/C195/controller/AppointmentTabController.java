package C195.controller;

import C195.model.Appointment;
import C195.helper.SimpleAlert;
import java.io.IOException;
import java.net.URL;
import java.text.DateFormatSymbols;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
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
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import C195.dao.AppointmentDbActions;

/**
 * FXML Controller class for the appointment dialog.
 * Populates a table containing appointment records from the client_schedule database.
 * Contains controls to sort, add, modify, and delete appointments.
 * Displays an error when no appointment is selected to modify.
 * 
 * @author mattjsharp
 */
public class AppointmentTabController extends Controller implements AppointmentDbActions {

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
    TableColumn<Appointment, String> appointmentDateColumn;
    
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
    
    @FXML
    RadioButton allRadioButton;
    
    @FXML
    Label sortLabel;

    /**
     *  Initializes the controller class.
     * 
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
        appointmentDateColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStartDate"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("startTime"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        appointmentUserIdColumn.setCellValueFactory(new PropertyValueFactory<>("userId"));

        // Setting the text for each of the column headers.
        appointmentIdColumn.setText("ID");
        appointmentTitleColumn.setText(l10n.getString("title"));
        appointmentDescriptionColumn.setText(l10n.getString("description"));
        appointmentLocationColumn.setText(l10n.getString("location"));
        appointmentContactIdColumn.setText(l10n.getString("contactId"));
        appointmentTypeColumn.setText(l10n.getString("type"));
        appointmentDateColumn.setText(l10n.getString("date"));
        appointmentStartColumn.setText(l10n.getString("start"));
        appointmentEndColumn.setText(l10n.getString("end"));
        appointmentCustomerIdColumn.setText(l10n.getString("customerId"));
        appointmentUserIdColumn.setText(l10n.getString("userId"));

        // Using a lambda expresson to implement ChangeListener functional interface to an Observable.
        // Used to update the table with different circumstances depending on which option is selected.
        appointmentSort.selectedToggleProperty().addListener((observable, oldVal, newVal) -> {
            if (newVal != null) {
                RadioButton selectedRadioButton = (RadioButton) newVal;
                String selectedValue = selectedRadioButton.getText().trim();
                
                if (selectedValue.equals("All"))
                    updateTable();
                else if (selectedValue.equals("Week"))
                    updateTable('w');
                else if (selectedValue.equals("Month"))
                    updateTable('m');                
            }
        });
        
        // Updateting the table
        updateTable();
        
        // Setting a place holder in case there are no appointments schedueled
        appointmentTable.setPlaceholder(new javafx.scene.control.Label("There are no Appointments Scheduled"));
    }

    /**
     * Updates the appointment table.
     * Queries the appointment table from the client_schedule database and stores each record a list.
     * Clears the current table replaces the records with the new List.
     * 
     * @param flag an optional flag used to filer the list.
     */
    private void updateTable(char... flag) {
        appointmentTable.getItems().clear();
        List<Appointment> appointments = getAppointments();
        sortLabel.setText("");
        
        try {
            List<Appointment> filteredAppointments = new ArrayList<>();
            LocalDateTime currentDate = LocalDateTime.now();
            LocalDateTime start;
            LocalDateTime end;
            String[] months = new DateFormatSymbols().getShortMonths();
            
            switch (flag[0]) {
                case 'm' -> {
                    start = LocalDateTime.of(currentDate.getYear(), currentDate.getMonthValue(), 1, 0, 0);
                    end = LocalDateTime.of(currentDate.getYear(), currentDate.plusMonths(1).getMonthValue(), 1, 0, 0);
                    sortLabel.setText(String.valueOf(start.getMonth()));
                }
                case 'w' -> {
                    LocalDateTime current = LocalDateTime.of(LocalDateTime.now().toLocalDate(), LocalTime.of(23, 59));
                    start = current.minusDays(current.getDayOfWeek().compareTo(DayOfWeek.MONDAY));
                    end = start.plusDays(7);
                    sortLabel.setText(months[start.getMonthValue() - 1] + " " + start.getDayOfMonth() + " - " + months[end.getMonthValue() - 1] + " " + end.minusDays(1).getDayOfMonth());
                }
                default -> {
                        appointmentTable.getItems().addAll(appointments);
                        return;
                }
            }
            
            for (Appointment appointment : appointments) {
                if ((appointment.getStartDate().isBefore(end) || appointment.getStartDate().isEqual(end)) && (appointment.getStartDate().isAfter(start) || appointment.getStartDate().isEqual(start))) {
                    filteredAppointments.add(appointment);
                }
            }
            
            appointmentTable.getItems().addAll(filteredAppointments);
            
        } catch (Exception e){
            sortLabel.setText("ALL");
            appointmentTable.getItems().addAll(appointments);
        }
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

        allRadioButton.setSelected(true);
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

            allRadioButton.setSelected(true);
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
                    SimpleAlert.simpleInformation("Delete Success", "ID: " + selectedAppointment.getId() + "\nTYPE: " + selectedAppointment.getType() + "\nAppointment deleted successfully.");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            }
        }
    }
}
