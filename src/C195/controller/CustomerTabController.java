package C195.controller;

import C195.dao.CustomerInAppointmentQuery;
import C195.dao.CustomerQuery;
import C195.dao.DeleteCustomer;
import C195.helper.SimpleAlert;
import C195.model.Customer;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerTabController extends Controller implements CustomerQuery, CustomerInAppointmentQuery, DeleteCustomer {

    @FXML
    TableView customerTable;

    @FXML
    TableColumn customerIdColumn;

    @FXML
    TableColumn customerNameColumn;

    public void addCustomer() {
        System.out.println("add");
    }

    public void modifyCustomer() {
        System.out.println("modify");

        Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            SimpleAlert.simpleWarning("No customer Selected", "No customer selected.");
        }
    }

    public void deleteCustomer() {
        Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            SimpleAlert.simpleWarning("No customer Selected", "No customer selected.");
        } else {
            int obligations = customerInAppointment(selectedCustomer);
            if (obligations > 0) {
                SimpleAlert.simpleWarning("Scheduling Error", "Customer cannot be deleted\n\nCustomer still has : " + obligations + " appointment(s) scheduled.");
            } else {
                if (SimpleAlert.simpleConfirm("Confirmation", "Are you sure you want to delete this customer?")) {
                    deleteCustomer(selectedCustomer.getId());
                    updateTable();
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            }
        }
    }

    private void updateTable() {
        List<Customer> customers = getCustomers();
        customerTable.getItems().clear();
        customerTable.getItems().addAll(customers);
    }

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        customerIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        customerNameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));

        customerIdColumn.setText(l10n.getString("customerId"));
        customerNameColumn.setText(l10n.getString("customerName"));

        List<Customer> customers = getCustomers();
        customerTable.getItems().addAll(customers);
    }

}
