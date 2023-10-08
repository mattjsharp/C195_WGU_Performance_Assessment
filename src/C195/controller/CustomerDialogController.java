package C195.controller;

import C195.helper.SQLDateFormatter;
import C195.helper.SimpleAlert;
import C195.model.Country;
import C195.model.Customer;
import C195.model.FirstLevelDivision;
import C195.model.User;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import C195.dao.CountryDbActions;
import C195.dao.CustomerDbActions;
import C195.dao.FirstLevelDivisionDbActions;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerDialogController extends Controller implements CountryDbActions, FirstLevelDivisionDbActions, CustomerDbActions {

    @FXML
    Button cancelButton, submitButton;

    @FXML
    Label customerFlagLabel, editedByLabel, createdByLabel;

    @FXML
    TextField idField, nameField, addressField, postalCodeField, phoneField;

    @FXML
    ComboBox<String> countryComboBox, divisionComboBox;

    // Creating maps to cashe values of ids to names and vice versa.
    private HashMap<Integer, String> countryMap = new HashMap<>();
    private HashMap<String, Integer> reverseCountryMap = new HashMap<>();
    private HashMap<Integer, String> divisionMap = new HashMap<>();
    private HashMap<String, Integer> reverseDivisionMap = new HashMap<>();
    
    private Customer customer;
    private boolean modified;

    /**
     * Closes the dialog window.
     */
    public void cancelDialog() {
        stage.close();
    }

    /**
     * Submits the dialog to create a new customer. Performs tedious logical and
     * validation checks to ensure appointments meet the correct criteria.
     */
    public void submitDialog() {
        String name = nameField.getText(),
                address = addressField.getText(),
                postalCode = postalCodeField.getText(),
                phone = phoneField.getText(),
                editedBy = User.getUser().getName();
        
        // setting these fields to an inital value that is not possible
        int countryId = -1, divisionId = -1;
        
        String errorString = "";
        boolean valid = true;
        
        // Checking if each of the TextFields is empty?
        if (name.isEmpty()) {
            errorString += "Name must not be blank\n\n";
            valid = false;
        }
        
        if (address.isEmpty()) {
            errorString += "Address must not be blank\n\n";
            valid = false;
        }
        
        if (postalCode.isEmpty()) {
            errorString += "Postal Code must not be blank\n\n";
            valid = false;
        }
        
        if (phone.isEmpty()) {
            errorString += "Phone number must not be blank\n\n";
            valid = false;
        }

        if (valid) {
            if (modified) {
                if (updateCustomer(name,
                        address,
                        postalCode,
                        phone,
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        reverseDivisionMap.get(divisionComboBox.getValue()),
                        customer.getId()
                )) {
                    stage.close();
                    SimpleAlert.simpleInformation("Success", "Customer was successfull modified by " + editedBy + ".");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Something went wrong with the database.");
                } 
            } else {
                if (insertCustomer(name,
                        address,
                        postalCode,
                        phone,
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        reverseDivisionMap.get(divisionComboBox.getValue())
                        )) {
                    stage.close();
                    SimpleAlert.simpleInformation("Success", "Customer was successfull added.");
                } else {
                    SimpleAlert.simpleError("SQL Error", "Something went wrong with the database.");
                }
            }
        } else {
            SimpleAlert.simpleWarning("Validation Error", errorString);
        }
         
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
        
        modified = true;
        
        editedByLabel.setText("Last Edited By");
        customerFlagLabel.setText("Modify Customer");
        idField.setText(String.valueOf(customer.getId()));
        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        postalCodeField.setText(customer.getPostalCode());
        phoneField.setText(customer.getPhone());
        createdByLabel.setText("Created By: " + customer.getCreatedBy());
        editedByLabel.setText("Last Modified By: " + customer.getLastUpdatedBy());
        countryComboBox.setValue(countryMap.get(getCountryCode(customer.getDivisionId())));
        setDivisions();
        divisionComboBox.setValue(divisionMap.get(customer.getDivisionId()));
    }

    public void setDivisions() {
        divisionComboBox.getItems().clear();
        List<FirstLevelDivision> divisions = getDivisions(reverseCountryMap.get(countryComboBox.getValue()));
        for (FirstLevelDivision division : divisions) {
            divisionMap.put(division.getId(), division.getName());
            reverseDivisionMap.put(division.getName(), division.getId());
            
            divisionComboBox.getItems().add(division.getName());
        }
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        for (Country country : getCountries()) {
            countryMap.put(country.getId(), country.getName());
            reverseCountryMap.put(country.getName(), country.getId());
            
            countryComboBox.getItems().add(country.getName());
        }
    }
}
