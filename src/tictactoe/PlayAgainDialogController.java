/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import static tictactoe.TheBoardController.newStage;

/**
 * FXML Controller class
 *
 * @author Me5a
 */
public class PlayAgainDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    PlayerFunctions playerFunctionsObj;
    TheBoardController boardObj;
    
    public void setPlayerFunctionsObj(PlayerFunctions obj){
        playerFunctionsObj=obj;
    }
    
    @FXML
    void exit(ActionEvent event) {

    }
    
    void setBoardObj(TheBoardController obj){
        boardObj=obj;
    }

    @FXML
    void playAgain(ActionEvent event) {
        boardObj.createBoard();
        boardObj.setTurnLbl(true);
        playerFunctionsObj.game.playAgain();
        TheBoardController.newStage.close();
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
