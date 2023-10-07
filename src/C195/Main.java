package C195;

import C195.dao.JDBC;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Main class.
 * Contains the main method which is the entry point of the program.
 * Launches the javaFX GUI application as well as opens and closes the database connection.
 * 
 * @author mattjsharp
 */
public class Main extends Application {
    /**
     * The entry point of the program.
     * Opens and closes the database.
     * Launches the javafx application.
     * 
     * @param args
     * @throws SQLException 
     */
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    /**
     * Initializes the JavaFX GUI.
     * Generates a scene graph from an FXML file to populate a scene which is loaded to the stage.
     * 
     * @param stage
     * @throws Exception
     */
    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Appoitment Schedulder");
        stage.setResizable(false);
        stage.show();
    }
}
