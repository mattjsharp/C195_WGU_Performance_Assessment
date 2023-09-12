package C195.controller;

import C195.dao.ContactQuery;
import C195.model.Contact;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class AppointmentDialogController extends Controller implements ContactQuery {
    
    @FXML
    Button submitButton, cancelButton;
    
    @FXML
    TextField 
        titleField,
        descriptionField,
        locationField,
        typeField,
        editedByField;
    
    @FXML
    DatePicker startDatePicker, endDatePicker;
    
    @FXML
    ComboBox<Integer> customerComboBox, userComboBox, contactComboBox;
            
    
    public void submitDialog(ActionEvent event) {
        
        stage.close();
    }
    
    public void cancelDialog(ActionEvent event) {
        stage.close();
    }

    /**
     * Initializes the controller class.
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Contact contact : getContacts()) {
            contactComboBox.getItems().add(contact.getId());
        }
    }    
    
}
