package C195.controller;

import C195.dao.AppointmentDbActions;
import C195.dao.CountryDbActions;
import C195.dao.CustomerDbActions;
import C195.model.Country;
import java.net.URL;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class CustomerBarChartController extends Controller implements AppointmentDbActions, CountryDbActions, CustomerDbActions {

    @FXML
    CategoryAxis countryAxis = new CategoryAxis();
    
    @FXML
    NumberAxis numberAxis = new NumberAxis();

    @FXML
    BarChart chart = new BarChart(countryAxis, numberAxis);
    
    @FXML
    ComboBox countryComboBox;
    
    private HashMap<String, Integer> countryMap = new HashMap<>();

    private List<Country> countires = getCountries();

    public void updateChart() {
        int id = countryMap.get(countryComboBox.getValue());
        System.out.println(id);
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (Country country : countires) {
            countryComboBox.getItems().add(country.getName());
            countryMap.put(country.getName(), country.getId());
        }
        
        countryComboBox.setValue(countires.get(0).getName());

        updateChart();
    }

}
