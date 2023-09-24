package C195.controller;

import C195.dao.CountryQuery;
import C195.dao.FirstLevelDivisionQuery;
import C195.dao.InsertCustomer;
import C195.dao.UpdateCustomer;
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

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerDialogController extends Controller implements CountryQuery, FirstLevelDivisionQuery, InsertCustomer, UpdateCustomer {

    @FXML
    Button cancelButton, submitButton;

    @FXML
    Label customerFlagLabel, editedByLabel;

    @FXML
    TextField idField, nameField, addressField, postalCodeField, phoneField, editedByField;

    @FXML
    ComboBox<String> countryComboBox, divisionComboBox;

    private HashMap<String, Integer> countryMap = new HashMap<>();
    private HashMap<Integer, String> divisionMap = new HashMap<>();
    
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
        
//        try {
//            countryId = countryComboBox.getValue();
//        }

        if (valid) {
            if (modified) {
                if (updateCustomer(name,
                        address,
                        postalCode,
                        phone,
                        SQLDateFormatter.formatDate(LocalDateTime.now().atZone(ZoneId.systemDefault()).withZoneSameInstant(ZoneId.of("UTC")).toLocalDateTime()),
                        editedBy,
                        1,
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
                        1
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
        editedByLabel.setText("Last Edited By");
        
        modified = true;
        customerFlagLabel.setText("Modify Customer");
        idField.setText(String.valueOf(customer.getId()));
        nameField.setText(customer.getName());
        addressField.setText(customer.getAddress());
        postalCodeField.setText(customer.getPostalCode());
        phoneField.setText(customer.getPhone());  
        editedByField.setText(User.getUser().getName());
        countryComboBox.setValue(getCountries(customer.getId()).get(0).getName());
        setDivisions();
        divisionComboBox.setValue(divisionMap.get(customer.getDivisionId()));
    }

    public void setDivisions() {
        divisionComboBox.getItems().clear();
        List<FirstLevelDivision> divisions = getDivisions(countryMap.get(countryComboBox.getValue()));
        for (FirstLevelDivision division : divisions) {
            divisionMap.put(division.getId(), division.getName());
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
        editedByField.setText(User.getUser().getName());
        
        for (Country country : getCountries()) {
            countryMap.put(country.getName(), country.getId());
            countryComboBox.getItems().add(country.getName());
        }
    }
}
