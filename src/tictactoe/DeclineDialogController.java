
package tictactoe;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

/**
 * FXML Controller class
 *
 * @author Salama
 */
public class DeclineDialogController implements Initializable {
 
   @FXML
    private Label userName;

    @FXML
    private JFXButton ok;

    ControlButtonsController sobj = null;
    
    public void setControlObject(ControlButtonsController obj){
        sobj=obj;
    }

    public void setUserName(String name){
         userName.setText(name);
    }
    
    @FXML
    public void accept()
    {
        ControlButtonsController.newStage.close();
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
