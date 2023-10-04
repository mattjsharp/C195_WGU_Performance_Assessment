package C195.controller;

import C195.dao.AppointmentDbActions;
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
 * FXML Controller class
 *
 * @author LabUser
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

    private HashMap<String, Integer> countryMap = new HashMap<>();
    private HashMap<Integer, String> divisionMap;

    private List<Country> countires = getCountries();
    private List<Customer> customers;
    private List<FirstLevelDivision> divisions;

    public void updateChart() {
        chart.getData().clear();
        
        int id = countryMap.get(countryComboBox.getValue());
        customers = getCustomersByCountry(countryMap.get(countryComboBox.getValue()));
        divisions = getDivisions(countryMap.get(countryComboBox.getValue()));
        divisionMap = new HashMap<>();
        LinkedList<ArrayList<Customer>> sortedCustomerList = new LinkedList<>();

        for (FirstLevelDivision division : divisions) {
            divisionMap.put(division.getId(), division.getName());
            ArrayList<Customer> customerList = new ArrayList<>();
            for (Customer customer : customers) {
                if (customer.getDivisionId() == division.getId()) {
                    customerList.add(customer);
                }
            }
            if (customerList.size() > 0) {
                sortedCustomerList.add(customerList);
            }
        }

        for (ArrayList<Customer> list : sortedCustomerList) {
            System.out.println(divisionMap.get(list.get(0).getDivisionId()) + " " + list.size());
            XYChart.Series<String, Integer> series = new XYChart.Series<>();
            series.setName(divisionMap.get(list.get(0).getDivisionId()));
            series.getData().add(new XYChart.Data<>(divisionMap.get(list.get(0).getDivisionId()), list.size()));
            chart.getData().add(series);
        }
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
