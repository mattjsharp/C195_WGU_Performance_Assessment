package C195;

import C195.dao.JDBC;
import java.sql.SQLException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author LabUser
 */
public class Main extends Application {
    public static void main(String[] args) throws SQLException {
        JDBC.openConnection();
        launch(args);
        JDBC.closeConnection();
    }

    @Override
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("view/Login.fxml"));
        stage.setScene(new Scene(root));
        stage.setTitle("Appoitment Schedulder");
        stage.setResizable(false);
        stage.show();
    }
}
