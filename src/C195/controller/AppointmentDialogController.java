package C195.controller;

import C195.dao.ContactQuery;
import C195.dao.CustomerQuery;
import C195.dao.InsertAppointment;
import C195.dao.UserQuery;
import C195.helper.SimpleAlert;
import C195.helper.DateFormatter;
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
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory.IntegerSpinnerValueFactory;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class AppointmentDialogController extends Controller implements DateFormatter, ContactQuery, UserQuery, CustomerQuery, InsertAppointment {

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
    ToggleGroup startAmpm, endAmpm;

    /**
     * 
     * @param event 
     */
    public void submitDialog(ActionEvent event) {
        String title = titleField.getText(),
                description = descriptionField.getText(),
                location = locationField.getText(),
                type = typeField.getText(),
                createdBy = editedByField.getText();

        LocalDate startDate = startDatePicker.getValue(), endDate = endDatePicker.getValue();
        // setting these fields to an inital value that is not possible
        int customerId = -1, userId = -1, contactId = -1;

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
        if (createdBy.isEmpty()) {
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
            userId = contactComboBox.getValue();
        } catch (Exception e) {
            errorString += "Contact ID cannot be blank\n\n";
            valid = false;
        }
        
        try {
            contactId = userComboBox.getValue();
        } catch (Exception e) {
            errorString += "User ID cannot be blank\n\n";
            valid = false;
        }
        
        // Properly adjusitng time for a 24 hour day and setting proper limits in case inproper inputs are set in the spinner.
        if (startAmpm.) {
            
        }
        
        // Final result
        if (valid) {
             if (insertAppointment(
                    title,
                    description,
                    location,
                    type,
                    DateFormatter.formatDate(LocalDateTime.of(startDate.getYear(), startDate.getMonth(), startDate.getDayOfMonth(), (int)startHourSpinner.getValue(), (int)startMinuteSpinner.getValue())),
                    DateFormatter.formatDate(LocalDateTime.of(endDate.getYear(), endDate.getMonth(), endDate.getDayOfMonth(), (int)endHourSpinner.getValue(), (int)endMinuteSpinner.getValue())),
                    DateFormatter.formatDate(LocalDateTime.now()),
                    createdBy,
                    DateFormatter.formatDate(LocalDateTime.now()),
                    createdBy,
                    customerId,
                    userId,
                    contactId
            )) {
                 stage.close();
            } else {
                SimpleAlert.simpleWarning("SQL Error", "Somthing went wrong with the database.");
            }
        } else {
            SimpleAlert.simpleWarning("Validation Error", errorString);
        }
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
