package C195.controller;

import C195.helper.SQLDateFormatter;
import C195.helper.SimpleAlert;
import C195.model.Appointment;
import C195.model.Contact;
import C195.model.Customer;
import C195.model.User;
import java.net.URL;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import C195.dao.AppointmentDbActions;
import C195.dao.ContactDbActions;
import C195.dao.CustomerDbActions;
import C195.dao.UserDbActions;
import java.util.List;

/**
 * FXML Controller class for the Add/Modify Appointment Dialog. Contains fields
 * which can be used to insert a new appointment record or modify an existing
 * one. Performs various logical and validation checks to ensure correct user
 * input.
 *
 * @author mattjsharp
 */
public class AppointmentDialogController extends Controller implements ContactDbActions, UserDbActions, CustomerDbActions, AppointmentDbActions {

    @FXML
    Button submitButton, cancelButton;

    @FXML
    TextField idField,
            appointmentField,
            titleField,
            descriptionField,
            locationField,
            typeField;

    @FXML
    DatePicker datePicker;

    @FXML
    ComboBox<String> customerComboBox, contactComboBox;

    @FXML
    Spinner startHourSpinner, startMinuteSpinner, endHourSpinner, endMinuteSpinner;

    @FXML
    RadioButton startPmRadioButton, endPmRadioButton;

    @FXML
    Label appointmentFlagLabel, createdByLabel, editedByLabel;

    private boolean modified;
    private Appointment appointment;

    private HashMap<String, Integer> customerMap = new HashMap<>();
    private HashMap<String, Integer> contactMap = new HashMap<>();

    private HashMap<Integer, String> reverseCustomerMap = new HashMap<>();
    private HashMap<Integer, String> reverseContactMap = new HashMap<>();

    /**
     * Submits the appointment. Performs tedious logical and validation checks
     * to ensure appointments meet the correct criteria.
     */
    public void submitDialog() {

        String title = titleField.getText(),
                description = descriptionField.getText(),
                location = locationField.getText(),
                type = typeField.getText(),
                editedBy = User.getUser().getName(),
                customer = "", contact = "", user = "";

        LocalDate startDate = datePicker.getValue();

        LocalDateTime start;
        LocalDateTime end;

        int startHour = (int) startHourSpinner.getValue(), startMinute = (int) startMinuteSpinner.getValue(),
                endHour = (int) endHourSpinner.getValue(), endMinute = (int) endMinuteSpinner.getValue(),
                userId = User.getUser().getId(),
                contactId = -1, customerId = -1;

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

        if (startDate == null) {
            errorString += "Date is required\n\n";
            valid = false;
        }

        if (customerComboBox.getValue() == null) {
            errorString += "Customer ID cannot be blank\n\n";
            valid = false;
        } else {
            customerId = customerMap.get(customerComboBox.getValue());
        }

        if (contactComboBox.getValue() == null) {
            errorString += "Contact ID cannot be blank\n\n";
            valid = false;
        } else {
            contactId = contactMap.get(contactComboBox.getValue());
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

        // Adjusting time zone to UTC and others to EST
        start = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), startHour, startMinute).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();
        end = LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), endHour, endMinute).atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime();

        LocalDateTime startEST = start.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();
        LocalDateTime endEST = end.atZone(ZoneId.of("UTC")).withZoneSameInstant(ZoneId.of("America/New_York")).toLocalDateTime();

        if (start.isAfter(end)) {
            errorString += "Start time cannot be greater than the end time.\n\n";
            valid = false;
        }

        if ((startEST.toLocalTime().isBefore(LocalTime.of(8, 0)) || startEST.toLocalTime().isAfter(LocalTime.of(22, 0)))
                || (endEST.toLocalTime().isBefore(LocalTime.of(8, 0)) || endEST.toLocalTime().isAfter(LocalTime.of(22, 0)))) {
            errorString += "Date cannot be schedlued outside of office hours:\n\t\t8:00am - 10:00pm EST\n\n";
            valid = false;
        }

        // Checking for appointment conflicts with a particular customer.
        if (valid) {
            List<Appointment> appointmentOverlaps = customerOverlapCheck(SQLDateFormatter.formatDate(start), SQLDateFormatter.formatDate(end), customerId, appointment != null ? appointment.getId() : 0);
            if (!appointmentOverlaps.isEmpty()) {
                errorString += "Customer has " + appointmentOverlaps.size() + " conflicting appointments:\n\n";
                valid = false;
                for (Appointment appointment : appointmentOverlaps) {
                    errorString += "\tID: " + appointment.getId() + "\tTitle: " + appointment.getTitle() + "\n";
                }
            }
        }

        // Final result
        if (valid) {
            if (modified) {
                if (updateAppointment(title,
                        description,
                        location,
                        type,
                        SQLDateFormatter.formatDate(start),
                        SQLDateFormatter.formatDate(end),
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        customerId,
                        userId,
                        contactId,
                        appointment.getId()
                )) {
                    stage.close();
                    SimpleAlert.simpleInformation("Success", "Appointment successfully modified by " + editedBy + ".");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            } else {
                if (insertAppointment(title,
                        description,
                        location,
                        type,
                        SQLDateFormatter.formatDate(start),
                        SQLDateFormatter.formatDate(end),
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        customerId,
                        userId,
                        contactId
                )) {
                    stage.close();
                    SimpleAlert.simpleInformation("Success", "Appointment added successfully.");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            }
        } else {
            SimpleAlert.simpleWarning("Validation Error", errorString);
        }
    }

    /**
     * Populates the form with data from the selected appointment.
     *
     * @param appointment The selected appointment from the appointment table.
     */
    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
        idField.setText(String.valueOf(appointment.getId()));

        int startHour = appointment.getStartDate().getHour(),
                startMinute = appointment.getStartDate().getMinute(),
                endHour = appointment.getEndDate().getHour(),
                endMinute = appointment.getEndDate().getMinute();

        modified = true;

        appointmentFlagLabel.setText("Modify Appointment");
        editedByLabel.setText("Last Edited by: ");

        // Formatting the time correctly.
        if (startHour >= 12) {
            startPmRadioButton.setSelected(true);
            if (startHour != 12) {
                startHour %= 12;
            }
        } else {
            if (startHour == 0) {
                startHour = 12;
            }
        }

        if (endHour >= 12) {
            endPmRadioButton.setSelected(true);
            if (endHour != 12) {
                endHour %= 12;
            }
        } else {
            if (endHour == 0) {
                endHour = 12;
            }
        }

        titleField.setText(appointment.getTitle());
        descriptionField.setText(appointment.getDescription());
        locationField.setText(appointment.getLocation());
        typeField.setText(appointment.getType());
        datePicker.setValue(appointment.getStartDate().toLocalDate());
        startHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12, startHour));
        startMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, startMinute));
        endHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12, endHour));
        endMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59, endMinute));
        createdByLabel.setText("Created By: " + appointment.getCreatedBy());
        editedByLabel.setText("Last Modified By: " + appointment.getLastUpdatedBy());

        customerComboBox.setValue(reverseCustomerMap.get(appointment.getCustomerId()));
        contactComboBox.setValue(reverseContactMap.get(appointment.getContactId()));
    }

    /**
     * Closes the dialog window.
     */
    public void cancelDialog() {
        stage.close();
    }

    /**
     * Initializes the controller class. Populates both the customer and contact
     * ComboBoxes. Sets the date picker to the current date.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {

        for (Contact contact : getContacts()) {
            contactMap.put(contact.getName(), contact.getId());
            reverseContactMap.put(contact.getId(), contact.getName());
            contactComboBox.getItems().add(contact.getName());
        }

        for (Customer customer : getCustomers()) {
            customerMap.put(customer.getName(), customer.getId());
            reverseCustomerMap.put(customer.getId(), customer.getName());
            customerComboBox.getItems().add(customer.getName());
        }

        datePicker.setValue(LocalDate.now());

        startHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12));
        startMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
        endHourSpinner.setValueFactory(new IntegerSpinnerValueFactory(1, 12));
        endMinuteSpinner.setValueFactory(new IntegerSpinnerValueFactory(0, 59));
    }

}
