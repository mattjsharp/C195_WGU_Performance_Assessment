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
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.layout.HBox;

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
    HBox optionsBox;

    /**
     * Initializes the controller class.
     *
     * @param url
     * @param rb
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            appointmentsTab.setContent(FXMLLoader.load(getClass().getResource("../view/AppointmentTab.fxml")));
        } catch (IOException e) {
            e.getMessage();
        }

        try {
            customersTab.setContent(FXMLLoader.load(getClass().getResource("../view/CustomerTab.fxml")));
        } catch (IOException e) {
            e.getMessage();
        }

        getTab(tabPane.getSelectionModel().getSelectedItem());
            
        appointmentsTab.setText(l10n.getString("appointments"));
        customersTab.setText(l10n.getString("customers"));

        tabPane.getSelectionModel().selectedItemProperty().addListener((observable, oldTab, newTab) -> {
            if (newTab != null) {
                getTab(newTab);
            }
        });
    }

    private void getTab(Tab tab) {
        try {
            if (tab.getId().equals(appointmentsTab.getId())) {
                optionsBox = FXMLLoader.load(getClass().getResource("../view/AppointmentOptionsBox.fxml"));
            }
        } catch (IOException e) {
            e.getMessage();
        }
    }
}
