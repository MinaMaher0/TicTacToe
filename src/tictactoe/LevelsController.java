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
import static tictactoe.MainGUI.primaryStage;

/**
 * FXML Controller class
 *
 * @author Me5a
 */
public class LevelsController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PlayerFunctions pF;
    @FXML
    void back_home(ActionEvent event) {
        try {
            Parent root;
            
            root=FXMLLoader.load(getClass().getResource("ControlButtons.fxml")) ;
            Scene scene=new Scene(root);
            MainGUI.primaryStage.setTitle("Home");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show();
            MainGUI.primaryStage.setResizable(true);
        } catch (IOException ex) {
            Logger.getLogger(LevelsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void setPlayerObj(PlayerFunctions obj){
        pF=obj;
    }
    
    @FXML
    void easy(ActionEvent event) {
        loadBoard();
        pF.playWithComuter("easy");
    }

    @FXML
    void hard(ActionEvent event) {
        loadBoard();
        pF.playWithComuter("hard");
    }

    @FXML
    void meduim(ActionEvent event) {
        loadBoard();
        pF.playWithComuter("medium");
    }
    
    public void loadBoard(){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TheBoard.fxml"));
            Parent root;
            root = (Parent)loader.load();
            TheBoardController boardObj=loader.getController();
            boardObj.SetPlayerFunctionObj(pF);
            boardObj.setComputerPlayer(true);
            boardObj.hideChatAndSave();
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("GameBoard");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show(); 
        } catch (Exception ex) {
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
