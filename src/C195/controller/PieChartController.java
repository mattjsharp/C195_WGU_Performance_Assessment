package C195.controller;

import C195.dao.AppointmentDbActions;
import C195.helper.PieChartSection;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map.Entry;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;

/**
 * FXML Controller class for the Appointment pie chart report.
 *
 * @author mattjsharp
 */
public class PieChartController extends Controller implements AppointmentDbActions {

    @FXML
    ComboBox pieMonthComboBox, pieYearComboBox;

    @FXML
    PieChart appointmentPieChart;
    
    @FXML
    Label totalLabel;

    ObservableList<PieChart.Data> pieChartData;

    private HashMap<String, Integer> months = new HashMap<>();

    {
        months.put("January", 1);
        months.put("February", 2);
        months.put("March", 3);
        months.put("April", 4);
        months.put("May", 5);
        months.put("June", 6);
        months.put("July", 7);
        months.put("August", 8);
        months.put("September", 9);
        months.put("October", 10);
        months.put("November", 11);
        months.put("December", 12);
    }
    private HashMap<Integer, String> reverseMonths = new HashMap<>();

    {
        for (Entry<String, Integer> entry : months.entrySet()) {
            reverseMonths.put(entry.getValue(), entry.getKey());
        }
    }

    /**
     * Updates the Pie chart.
     * Queries the client_schedule database for appointment entries matching the input provided.
     */
    public void setChart() {
        pieChartData = FXCollections.observableArrayList();
        List<PieChartSection> appointments = getDistinctAppointments(months.get(pieMonthComboBox.getValue()), (int) pieYearComboBox.getValue());
        int total = 0;
        
        for (PieChartSection appointment : appointments) {
            pieChartData.add(new PieChart.Data(appointment.TYPE, appointment.NUMBER));
            total += appointment.NUMBER;
        }

        appointmentPieChart.setData(pieChartData);
        
        // Updating the total label.
        totalLabel.setText("Total: " + total);
        
        // Updating the year combobox.
        int[] range = getYearRange();
        for (int i = range[1]; i <= range[0]; i++) {
            if (i < range[1] || i > range[0])
                pieYearComboBox.getItems().add(i);
        }
    }

    /**
     * Initializes the controller class.
     * Loads options in to the ComboBoxes and sets an initial value.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        for (int i = 1; i <= 12; i++) pieMonthComboBox.getItems().add(reverseMonths.get(i));
        
        int[] range = getYearRange();
        for (int i = range[1]; i <= range[0]; i++) pieYearComboBox.getItems().add(i);
        
        pieMonthComboBox.setValue(reverseMonths.get(LocalDateTime.now().getMonthValue()));
        pieYearComboBox.setValue(LocalDateTime.now().getYear());
        setChart();
    }
}
