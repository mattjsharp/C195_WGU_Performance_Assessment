/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import C195.l10n.L10n;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 *
 * @author LabUser
 */
public abstract class Controller implements L10n, Initializable {
    protected Stage stage;
    
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
