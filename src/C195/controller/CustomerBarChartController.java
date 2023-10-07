package C195.controller;

import C195.dao.CountryDbActions;
import C195.dao.CustomerDbActions;
import C195.dao.FirstLevelDivisionDbActions;
import C195.model.Country;
import C195.model.Customer;
import C195.model.FirstLevelDivision;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.ComboBox;

/**
 * FXML Controller class for the customer bar chart to be loaded into the report tab.
 * 
 *
 * @author mattjsharp
 */
public class CustomerBarChartController extends Controller implements CountryDbActions, CustomerDbActions, FirstLevelDivisionDbActions {

    @FXML
    CategoryAxis countryAxis;

    @FXML
    NumberAxis numberAxis;

    @FXML
    BarChart chart;

    @FXML
    ComboBox countryComboBox;

    private final HashMap<String, Integer> countryMap = new HashMap<>();
    private HashMap<Integer, String> divisionMap;

    private final List<Country> countires = getCountries();
    private List<Customer> customers;
    private List<FirstLevelDivision> divisions;


    /**
     * Updates the chart based off of the country based off of the country selected in the combo box.
     */ 
    
    public void updateChart() {
        // Clears the current chart.
        chart.getData().clear();
        
        customers = getCustomersByCountry(countryMap.get(countryComboBox.getValue()));
        divisions = getDivisions(countryMap.get(countryComboBox.getValue()));
        divisionMap = new HashMap<>();
        LinkedList<ArrayList<Customer>> sortedCustomerList = new LinkedList<>();

        // Sorts every every customer from each country into its asssociated division.
        for (FirstLevelDivision division : divisions) {
            divisionMap.put(division.getId(), division.getName());
            ArrayList<Customer> customerList = new ArrayList<>();
            for (Customer customer : customers) {
                if (customer.getDivisionId() == division.getId()) {
                    customerList.add(customer);
                }
            }
            if (!customerList.isEmpty()) {
                sortedCustomerList.add(customerList);
            }
        }

        // Created new bars to populate the chart.
        for (ArrayList<Customer> list : sortedCustomerList) {
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(divisionMap.get(list.get(0).getDivisionId()) + " (" + list.size() + ")");
            series.getData().add(new XYChart.Data<>(divisionMap.get(list.get(0).getDivisionId()), list.size()));
            chart.getData().add(series);
        }
    }

    /**
     * Initializes the controller class.
     * Populates the country combo box then updates the chart based off the the input.
     * 
     * @param url.
     * @param rb
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
