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
import javafx.scene.control.Button;
import javafx.scene.control.Label;
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
    
    @FXML
    private Label lbl;
    
    @FXML
    private Button playAgainBTN;
    
    public void setPlayerFunctionsObj(PlayerFunctions obj){
        playerFunctionsObj=obj;
    }
    
    void loadHomePage(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlButtons.fxml"));
            Parent root;
            root = (Parent)loader.load();
            ControlButtonsController controll=loader.getController();
            controll.setPlayerObj(playerFunctionsObj);
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("Home Page");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(PlayAgainDialogController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void exitAction(){
        loadHomePage();
        playerFunctionsObj.exitGame();
        if (playerFunctionsObj.isPlayWithComputer())
        TheBoardController.newStage.close();
    }
    
    @FXML
    void exit(ActionEvent event) {
        exitAction();
    }
    
    void setBoardObj(TheBoardController obj){
        boardObj=obj;
    }

    @FXML
    void playAgain(ActionEvent event) {
        boardObj.createBoard();
        boardObj.setTurnLbl(true);
        playerFunctionsObj.playAgain();
        if (playerFunctionsObj.isPlayWithComputer())
            TheBoardController.newStage.close();
        else{
            playAgainBTN.setVisible(false);
            lbl.setVisible(true);
            lbl.setText("Wait until the other player click play again");
        }
    }

    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbl.setVisible(false);
    }    
    
}
