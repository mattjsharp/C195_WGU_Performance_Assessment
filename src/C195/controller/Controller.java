/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package C195.controller;

import java.util.Locale;
import java.util.ResourceBundle;

/**
 *
 * @author LabUser
 */
public abstract class Controller {
    
    public final ResourceBundle l10n;
    
    Controller() {
        l10n = ResourceBundle.getBundle("C195/L10n/login", Locale.getDefault());
    }
    
}
