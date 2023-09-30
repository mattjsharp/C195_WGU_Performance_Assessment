package C195.controller;

import C195.helper.LoginActivityLogger;
import C195.helper.SimpleAlert;
import C195.model.User;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.stage.Stage;
/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class MainController extends Controller {

    @FXML
    TabPane tabPane;

    @FXML
    Tab appointmentsTab, customersTab, reportsTab;
    
    @FXML
    Label loggedInAsLabel, userNameLabel;

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
        reportsTab.setText(l10n.getString("reports"));
        loggedInAsLabel.setText(l10n.getString("loggedInAs"));
        userNameLabel.setText(User.getUser().getName());
    }

    private void initMain() throws IOException {
        FXMLLoader appointmentTabLoader = new FXMLLoader(getClass().getResource("../view/AppointmentTab.fxml"));
        FXMLLoader customerTabLoader = new FXMLLoader(getClass().getResource("../view/CustomerTab.fxml"));
        FXMLLoader reportTabLoader = new FXMLLoader(getClass().getResource("../view/ReportTab.fxml"));
        
        appointmentsTab.setContent(appointmentTabLoader.load());
        customersTab.setContent(customerTabLoader.load());
        reportsTab.setContent(reportTabLoader.load());
        
        appointmentTabController = appointmentTabLoader.getController();
        customerTabController = customerTabLoader.load();
    }
    
    public void logOut(ActionEvent e) throws IOException {
        if (SimpleAlert.simpleConfirm("LOGOUT", "Are you sure you want to Logout?")) {
            LoginActivityLogger logger = LoginActivityLogger.getInstance();
            logger.newLog("LOGOUT: \"" + User.getUser().getName() + "\" logged out successfully");
            User.setUser(null);
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            Scene scene = new Scene(FXMLLoader.load(getClass().getResource("../view/Login.fxml")));
            stage.setScene(scene);
            stage.show();
        }
    }
    
    public void exit(ActionEvent e) {
        if (SimpleAlert.simpleConfirm("EXIT", "Are you sure you want to exit the application?")) {
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }
}
