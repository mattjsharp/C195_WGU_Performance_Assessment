package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class MainController extends Controller {

    @FXML
    TabPane tabPane;

    @FXML
    Tab appointmentsTab, customersTab;

    Controller optionBoxController;
    Controller appointmentTabController;
    Controller customerTabController;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            initMain();
        } catch (IOException e) {
            e.getMessage();
        }

        appointmentsTab.setText(l10n.getString("appointments"));
        customersTab.setText(l10n.getString("customers"));
    }

    private void initMain() throws IOException {
        FXMLLoader appointmentTabLoader = new FXMLLoader(getClass().getResource("../view/AppointmentTab.fxml"));
        FXMLLoader customerTabLoader = new FXMLLoader(getClass().getResource("../view/CustomerTab.fxml"));
        
        appointmentsTab.setContent(appointmentTabLoader.load());
        customersTab.setContent(customerTabLoader.load());
        
        appointmentTabController = appointmentTabLoader.getController();
        customerTabController = customerTabLoader.load();
    }
}
