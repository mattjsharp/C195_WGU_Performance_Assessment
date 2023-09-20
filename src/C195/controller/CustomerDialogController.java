package C195.controller;

import C195.dao.CountryQuery;
import C195.dao.FirstLevelDivisionQuery;
import C195.model.Country;
import C195.model.Customer;
import C195.model.FirstLevelDivision;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerDialogController extends Controller implements CountryQuery, FirstLevelDivisionQuery {
    
    @FXML
    Button cancelButton, submitButton;
    
    @FXML
    ComboBox<String> countryComboBox, divisionComboBox;
    
    private HashMap<String, Integer> countryMap = new HashMap<>();
    
    /**
     * Closes the dialog window. 
     */
    public void cancelDialog() {
        stage.close();
    }
    
    /**
     * Submits the dialog to create a new customer.
     * Performs tedious logical and validation checks to ensure appointments meet the correct criteria. 
     */
    public void submitDialog() {
        stage.close();
    }
    
    public void setCustomer(Customer customer) {
        System.out.println("Modified");
    }
    
    public void setDivisions() { 
        divisionComboBox.getItems().clear();
        List<FirstLevelDivision> divisions = getDivisions(countryMap.get(countryComboBox.getValue()));
        for (FirstLevelDivision division : divisions) {
            divisionComboBox.getItems().add(division.getName());
        }
    }
    
    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Country country : getCountries()) {
            countryMap.put(country.getName(), country.getId());
            countryComboBox.getItems().add(country.getName());
        }
    }  
}
