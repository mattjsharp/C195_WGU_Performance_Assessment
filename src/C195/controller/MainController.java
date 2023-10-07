package C195.controller;

import C195.dao.AppointmentDbActions;
import C195.helper.LoginActivityLogger;
import C195.helper.SimpleAlert;
import C195.model.Appointment;
import C195.model.User;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
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
 * FXML Controller class for the main application page.
 * Contains tabs that load separate pages to perform various functions.
 * Updates/Reloads each tab whenever a user changes tabs.
 * Provides capabilites to logout and exit from the application.
 *
 * @author mattjsharp
 */
public class MainController extends Controller implements AppointmentDbActions {

    @FXML
    TabPane tabPane;

    @FXML
    Tab appointmentsTab, customersTab, reportsTab;

    @FXML
    Label loggedInAsLabel, userNameLabel;

    private FXMLLoader appointmentTabLoader;
    private FXMLLoader customerTabLoader;
    private FXMLLoader reportTabLoader;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            setTab();
        } catch (IOException e) {
            e.getMessage();
        }

        appointmentsTab.setText(l10n.getString("appointments"));
        customersTab.setText(l10n.getString("customers"));
        reportsTab.setText(l10n.getString("reports"));
        loggedInAsLabel.setText(l10n.getString("loggedInAs"));
        userNameLabel.setText(User.getUser().getName());

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            try {
                setTab();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        });
        
        checkAppointments();
    }

    /**
     * Method called whenever the user switches tabs.
     * Loads every fxml document for every tab so updates are tracked.
     * 
     * @throws IOException 
     */
    private void setTab() throws IOException {
        appointmentTabLoader = new FXMLLoader(getClass().getResource("../view/AppointmentTab.fxml"));
        customerTabLoader = new FXMLLoader(getClass().getResource("../view/CustomerTab.fxml"));
        reportTabLoader = new FXMLLoader(getClass().getResource("../view/ReportTab.fxml"));

        appointmentsTab.setContent(appointmentTabLoader.load());
        customersTab.setContent(customerTabLoader.load());
        reportsTab.setContent(reportTabLoader.load());
    }

    /**
     * Logs the user out.
     * Takes the user back to the login screen after a confirmation dialog.
     * Resets the logged in user and writes to the activity log.
     * Called whenever the logout button is pressed.
     * 
     * @param e
     * @throws IOException 
     */
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

    /**
     * Exits the application.
     * Awaits user confirmation to exit once the exit button is pressed and acts on the provided response.
     * 
     * @param e 
     */
    public void exit(ActionEvent e) {
        if (SimpleAlert.simpleConfirm("EXIT", "Are you sure you want to exit the application?")) {
            stage = (Stage) ((Node) e.getSource()).getScene().getWindow();
            stage.close();
        }
    }
    
    /**
     * Checks if there are any appointments within 15 minutes.
     * Queries the appointment table and checks each appointment if it meets the time criteria.
     * Displays a message displaying either upcoming appointments or that none are near.
     */
    private void checkAppointments() {
        int appointmentCount = 0;
        String appointmentDescription = "";
        List<Appointment> appointments = getAppointments();
        for (Appointment appointment : appointments) {
            int minutesDifference = (int) ChronoUnit.MINUTES.between(LocalDateTime.now(), appointment.getStartDate());
            if (minutesDifference <= 15 && minutesDifference > 0) {
                appointmentDescription += "ID: " + appointment.getId() + "\tSTART: " + appointment.getStartTime() + " " + appointment.getFormattedStartDate() + "\tTITLE: " + appointment.getTitle() + "\n\n";
                appointmentCount++;
            }
        }

        if (appointmentCount > 0) {
            SimpleAlert.simpleWarning("" + appointmentCount + " Near Appointments", "There are " + appointmentCount + " appointment(s) within the near future:\n\n" + appointmentDescription);
        } else {
            SimpleAlert.simpleInformation("No Appointments", "There are no appointments scheduded in the near future.");
        }
    }
}
