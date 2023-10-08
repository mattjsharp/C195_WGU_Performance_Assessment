package C195.controller;

import C195.l10n.L10n;
import javafx.fxml.Initializable;
import javafx.stage.Stage;

/**
 * Base class for FXML controller classes.
 * Contains a resource bundle and a method set the stage.
 * 
 * @author mattjsharp
 */
public abstract class Controller implements L10n, Initializable {
    protected Stage stage;
    
    /**
     * Can be used to set the current stage.
     * Useful for dialogues.
     * 
     * @param stage The stage to be set.
     */
    public void setStage(Stage stage) {
        this.stage = stage;
    }
}
