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
    int pOneId,pTwoId;
    PlayerFunctions pFunctions=new PlayerFunctions();
    SignInController signInController= new SignInController();
    ServerSideClass sSC= new ServerSideClass();
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
        pFunctions.leaveGame(pOneId,pTwoId);
        //sSC.enableInviteButton(pOneId, pTwoId);
        ControlButtonsController.newStage.close();
        signInController.sign_in_sucess();
       
    }
    
    @FXML
    public void leave()
    {
        ControlButtonsController.newStage.close();
        sSC.sendLeaveGame(pOneId, pTwoId);
        signInController.sign_in_sucess();
        sSC.sendUsers();
    }
    
    public void setPlayersID(int p1,int p2)
    {
        this.pOneId=p1;
        this.pTwoId=p2;
                System.out.println("Players ID seted");
    }
    
    public void leaveGame()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("LeaveGameDialog.fxml"));
            Parent root;
            root = (Parent)loader.load();;
            Scene scene=new Scene(root);
            newStage.setTitle("Leave");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
