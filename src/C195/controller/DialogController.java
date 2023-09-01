/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;

/**
 *
 * @author LabUser
 */
public abstract class DialogController extends Controller {
    
    
    @FXML
    public Button cancelButton, submitButton;
    
    public void cancelDialog() {
        dialog.setResult(ButtonType.OK);
        dialog.close();
    }
    
    public void submitDialog() {
        dialog.setResult(ButtonType.CANCEL);
        dialog.close();
    }
    
}
