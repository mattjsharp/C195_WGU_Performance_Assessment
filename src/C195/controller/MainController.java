/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.Pane;

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

    @FXML
    Pane optionBox;

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
        
        getTab(tabPane.getSelectionModel().getSelectedItem());

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                try {
                    getTab(newTab);
                } catch (IOException e) {
                    e.getMessage();
                }
            }
        });

    }

    private void getTab(Tab tab) throws IOException {

        if (tab.getId().equals(appointmentsTab.getId())) {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../view/AppointmentOptionsBox.fxml"));
            Parent root = loader.load();
            optionBoxController = loader.getController();
            optionBox.getChildren().add(root);
        } else {
            optionBox.getChildren().clear();
            optionBoxController = null;
        }
    }
}
