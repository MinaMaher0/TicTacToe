/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Server.*;
import com.jfoenix.controls.JFXButton;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static tictactoe.ControlButtonsController.newStage;

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
        System.out.println("hellooooooo");
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
