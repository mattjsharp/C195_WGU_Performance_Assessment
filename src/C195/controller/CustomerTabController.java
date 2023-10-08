package C195.controller;

import C195.helper.SimpleAlert;
import C195.model.Customer;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import C195.dao.CustomerDbActions;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerTabController extends Controller implements CustomerDbActions {

    @FXML
    TableView customerTable;

    @FXML
    TableColumn<Customer, Integer> customerIdColumn, customerDivisionColumn;

    @FXML
    TableColumn<Customer, String> customerNameColumn, customerZipColumn, customerPhoneColumn;

    public void addCustomer() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CustomerDialog.fxml"));
        Parent root = loader.load();

        Stage dialogStage = new Stage();
        dialogStage.initStyle(StageStyle.UTILITY);
        dialogStage.initModality(Modality.APPLICATION_MODAL);
        dialogStage.setTitle("Add Customer");

        Controller controller = loader.getController();
        controller.setStage(dialogStage);

        Scene scene = new Scene(root);
        dialogStage.setScene(scene);
        dialogStage.showAndWait();

        updateTable();
    }

    public void modifyCustomer() throws IOException {

        Customer selectedCustomer = (Customer) customerTable.getSelectionModel().getSelectedItem();

        if (selectedCustomer == null) {
            SimpleAlert.simpleWarning("No Customer Selected", "No Customer Selected");
        } else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/CustomerDialog.fxml"));
            Parent root = loader.load();

            Stage dialogStage = new Stage();
            dialogStage.initStyle(StageStyle.UTILITY);
            dialogStage.initModality(Modality.APPLICATION_MODAL);
            dialogStage.setTitle("Modify Customer");

            CustomerDialogController controller = loader.getController();
            controller.setStage(dialogStage);
            controller.setCustomer(selectedCustomer);

            Scene scene = new Scene(root);
            dialogStage.setScene(scene);
            dialogStage.showAndWait();

            updateTable();
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
                    SimpleAlert.simpleInformation("Delete Success", selectedCustomer.getName() + " (" + selectedCustomer.getId() + ") deleted successfully.");
                    updateTable();
                } else {
                    SimpleAlert.simpleError("SQL Error", "Somthing went wrong with the database.");
                }
            }
        }
    }

    /**
     * Updates the customer table. Queries the customer table from the
     * client_schedule database and stores each record a list. Clears the
     * current table replaces the records with the new List.
     */
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
        customerZipColumn.setCellValueFactory(new PropertyValueFactory<>("postalCode"));
        customerPhoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        customerDivisionColumn.setCellValueFactory(new PropertyValueFactory<>("divisionId"));

        customerIdColumn.setText("ID");
        customerNameColumn.setText(l10n.getString("customerName"));
        customerZipColumn.setText(l10n.getString("postalCode"));
        customerPhoneColumn.setText(l10n.getString("phone"));
        customerDivisionColumn.setText(l10n.getString("division"));

        List<Customer> customers = getCustomers();
        customerTable.getItems().addAll(customers);
    }

}
