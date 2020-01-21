/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;


/**
 * FXML Controller class
 *
 * @author DELL
 */
public class LeaveGameDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PlayerFunctions pFunctions=new PlayerFunctions();

    
    @FXML
    private JFXButton yesSave;

    @FXML
    private JFXButton noLeave;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
    @FXML
    public void saveGame()
    {
        pFunctions.leaveGame();
        ControlButtonsController.newStage.close();
        pFunctions.exitGame();
    }
    
    @FXML
    public void leave()
    {
        ControlButtonsController.newStage.close();
        pFunctions.exitGame();
    }
    public void setPlayerFunctionsObj(PlayerFunctions obj){
        pFunctions=obj;
    }

}
