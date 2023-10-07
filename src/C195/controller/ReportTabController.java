package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.HBox;

/**
 * FXML Controller class for the customer bar chart.
 *
 * @author mattjsharp
 */
public class ReportTabController extends Controller {
    
    @FXML
    ScrollPane scrollPane;
    
    private HBox reportBox = new HBox();
    
    private void loadCharts() throws IOException {
        FXMLLoader pieChartLoader = new FXMLLoader(getClass().getResource("../view/PieChart.fxml"));
        FXMLLoader barChartLoader = new FXMLLoader(getClass().getResource("../view/CustomerBarChart.fxml"));
        FXMLLoader contactTableLoader = new FXMLLoader(getClass().getResource("../view/ContactTabeReport.fxml"));
        reportBox.getChildren().add(pieChartLoader.load());
        reportBox.getChildren().add(barChartLoader.load());
        reportBox.getChildren().add(contactTableLoader.load());
        
        scrollPane.setContent(reportBox);
        
    }

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try {
            loadCharts();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }    
    
}
