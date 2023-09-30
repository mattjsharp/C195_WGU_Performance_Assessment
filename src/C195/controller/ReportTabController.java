package C195.controller;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;

/**
 * FXML Controller class
 *
 * @author LabUser
 */
public class ReportTabController extends Controller {
    
    @FXML
    ScrollPane scrollPane;
    
    private void loadCharts() throws IOException {
        FXMLLoader pieChartLoader = new FXMLLoader(getClass().getResource("../view/PieChart.fxml"));
        
        scrollPane.setContent(pieChartLoader.load());
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
