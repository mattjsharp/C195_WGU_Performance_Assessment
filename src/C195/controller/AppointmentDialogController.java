package C195.controller;

import C195.dao.ContactQuery;
import C195.dao.CustomerQuery;
import C195.dao.InsertAppointment;
import C195.dao.UpdateAppointment;
import C195.dao.UserQuery;
import C195.helper.SimpleAlert;
import C195.helper.DateFormatter;
import C195.model.Appointment;
import C195.model.Contact;
import C195.model.Customer;
import C195.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class AppointmentDialogController extends Controller implements DateFormatter, ContactQuery, UserQuery, CustomerQuery, InsertAppointment, UpdateAppointment {

    @FXML
    Button submitButton, cancelButton;

    @FXML
    TextField titleField,
            descriptionField,
            locationField,
            typeField,
            editedByField;

    @FXML
    DatePicker startDatePicker, endDatePicker;

    @FXML
    ComboBox<Integer> customerComboBox, userComboBox, contactComboBox;

    @FXML
    Spinner startHourSpinner, startMinuteSpinner, endHourSpinner, endMinuteSpinner;

    @FXML
    RadioButton startPmRadioButton, endPmRadioButton;

    @FXML
    Label appointmentFlagLabel, editedByLabel;

    private boolean modified;
    private Appointment appointment;

    /**
     *
     * @param event
     */
    public void submitDialog(ActionEvent event) {
        String title = titleField.getText(),
                description = descriptionField.getText(),
                location = locationField.getText(),
                type = typeField.getText(),
                editedBy = editedByField.getText();

        LocalDate startDate = startDatePicker.getValue(), endDate = endDatePicker.getValue();
        // setting these fields to an inital value that is not possible
        int customerId = -1, userId = -1, contactId = -1,
                startHour = (int) startHourSpinner.getValue(), startMinute = (int) startMinuteSpinner.getValue(),
                endHour = (int) startHourSpinner.getValue(), endMinute = (int) endMinuteSpinner.getValue();

        String errorString = "";
        boolean valid = true;

        // Simple but tedious form validation.
        if (title.isEmpty()) {
            errorString += "Title is required\n\n";
            valid = false;
        }
        if (description.isEmpty()) {
            errorString += "Description is required\n\n";
            valid = false;
        }
        if (location.isEmpty()) {
            errorString += "Location is required\n\n";
            valid = false;
        }
        if (type.isEmpty()) {
            errorString += "Appointment Type is required\n\n";
            valid = false;
        }
        if (editedBy.isEmpty()) {
            errorString += "A description is required\n\n";
            valid = false;
        }

        if (startDate == null) {
            errorString += "Start date is required\n\n";
            valid = false;
        }

        if (endDate == null) {
            errorString += "End date is required\n\n";
            valid = false;
        }

        // retrieving absent primateves thows errors that need to be dealt with
        try {
            customerId = customerComboBox.getValue();
        } catch (Exception e) {
            errorString += "Customer ID cannot be blank\n\n";
            valid = false;
        }

        try {
            contactId = contactComboBox.getValue();
        } catch (Exception e) {
            errorString += "Contact ID cannot be blank\n\n";
            valid = false;
        }

        try {
            userId = userComboBox.getValue();
        } catch (Exception e) {
            errorString += "User ID cannot be blank\n\n";
            valid = false;
        }

        // Formatting time.
        if (startPmRadioButton.isSelected()) {
            if (startHour != 12) {
                startHour += 12;
            }
        } else {
            if (startHour == 12) {
                startHour = 0;
            }
        }

        if (endPmRadioButton.isSelected()) {
            if (endHour != 12) {
                endHour += 12;
            }
        } else {
            if (endHour == 12) {
                endHour = 0;
            }
        }

        // Final result
        if (valid) {
            /////////////////////////////////
            System.out.println("Start Hour: " + startHour + "\nEnd Hour: " + endHour);
            ////////////////////////////////
            if (modified) {
                if (updateAppointment(title,
                        description,
                        location,
                        type,
                        DateFormatter.formatDate(LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMinute)),
                        DateFormatter.formatDate(LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endHour, endMinute)),
                        DateFormatter.formatDate(LocalDateTime.now()),
                        editedBy,
                        customerId,
                        userId,
                        contactId,
                        appointment.getId()
                )) {
                    stage.close();
                } else {
                    SimpleAlert.simpleWarning("SQL Error", "Somthing went wrong with the database.");
                }
            } else {
                if (insertAppointment(title,
                        description,
                        location,
                        type,
                        DateFormatter.formatDate(LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMinute)),
                        DateFormatter.formatDate(LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), endHour, endMinute)),
                        DateFormatter.formatDate(LocalDateTime.now()),
                        editedBy,
                        DateFormatter.formatDate(LocalDateTime.now()),
                        editedBy,
                        customerId,
                        userId,
                        contactId
                )) {
                    stage.close();
                } else {
                    SimpleAlert.simpleWarning("SQL Error", "Somthing went wrong with the database.");
                }
            }
        } else {
            SimpleAlert.simpleWarning("Validation Error", errorString);
        }
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;

        int startHour = appointment.getStartDate().getHour(),
                startMinute = appointment.getStartDate().getMinute(),
                endHour = appointment.getEndDate().getHour(),
                endMinute = appointment.getEndDate().getMinute();

        modified = true;

        appointmentFlagLabel.setText("Modify Appointment");
        editedByLabel.setText("Edited by");

        // Formatting the time correctly.
        if (startHour >= 12) {
            startPmRadioButton.setSelected(true);
            startHour %= 12;
        } else {
            if (startHour == 0) {
                startHour = 12;
            }
        }

        if (endHour >= 12) {
            endPmRadioButton.setSelected(true);
            endHour %= 12;
        } else {
            if (endHour == 0) {
                endHour = 12;
            }
        }

        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        startDatePicker.setValue(appointment.getStartDate().toLocalDate());
        startHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12, startHour));
        startMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, startMinute));
        endDatePicker.setValue(appointment.getEndDate().toLocalDate());
        endHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12, endHour));
        endMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, endMinute));
        editedByField.setText(appointment.getFormattedLastUpdatedBy());

        userComboBox.setValue(appointment.getUserId());
        customerComboBox.setValue(appointment.getCustomerId());
        contactComboBox.setValue(appointment.getContactId());

    }

    public void cancelDialog(ActionEvent event) {
        stage.close();
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Contact contact : getContacts()) {
            contactComboBox.getItems().add(contact.getId());
        }

        for (User user : getUsers()) {
            userComboBox.getItems().add(user.getId());
        }

        for (Customer customer : getCustomers()) {
            customerComboBox.getItems().add(customer.getId());
        }

        startHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12));
        startMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
        endHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12));
        endMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
    }

}
