package C195.controller;

import C195.dao.AppointmentDbActions;
import C195.dao.ContactDbActions;
import C195.model.Appointment;
import C195.model.Contact;
import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class for the Contact bar chart report.
 *
 * @author mattjsharp
 */
public class ContactTabeReportController extends Controller implements AppointmentDbActions, ContactDbActions {

    @FXML
    ComboBox contactComboBox;
    
    @FXML
    TableView appointmentTable;

    @FXML
    TableColumn<Appointment, Integer> appointmentIdColumn, customerIdColumn;
    
    @FXML
    Label contactIdLabel;
   
    @FXML
    TableColumn<Appointment, String> appointmentTitleColumn, 
            appointmentTypeColumn, 
            appointmentDescriptionColumn, 
            appointmentStartColumn, 
            appointmentEndColumn;
    
    private List<Contact> contacts;
    private List<Appointment> appointments;
    
    private HashMap<String, Integer> contactMap = new HashMap<>();

    /**
     * Updates the barchart based using the input in the Country combo box.
     */
    public void setCustomer() {
        int id = contactMap.get(contactComboBox.getValue());
        contactIdLabel.setText(String.valueOf(id));
        appointments = getAppointmentsByContact(id);
        
        appointmentTable.getItems().clear();
        appointmentTable.getItems().addAll(appointments);
    }

    /**
     * Initializes the controller class.
     * Populates the Country ComboBox with countries to be used later.
     * 
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        appointmentIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        appointmentTitleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        appointmentDescriptionColumn.setCellValueFactory(new PropertyValueFactory<>("description"));
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("customerId"));
        appointmentTypeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        appointmentStartColumn.setCellValueFactory(new PropertyValueFactory<>("formattedStartDate"));
        appointmentEndColumn.setCellValueFactory(new PropertyValueFactory<>("endTime"));
        
        contacts = getContacts();
        
        for (Contact contact : contacts) {
            contactComboBox.getItems().add(contact.getName());
            contactMap.put(contact.getName(), contact.getId());
        }
        contactComboBox.setValue(contacts.get(0).getName());
        
        setCustomer();
    }

}
