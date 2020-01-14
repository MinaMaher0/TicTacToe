
package tictactoe;

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

    /**
     * Initializes the controller class.
     */
    PlayerFunctions playerfunc ;
    ControlButtonsController invite=null;
    
    public void setPlayer(PlayerFunctions p)
    {
        playerfunc = p;
    }
    
    String userName = new String();
    
    
    
    public void setUserName(String name){
         userName=name;
    }
    
   
   
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
