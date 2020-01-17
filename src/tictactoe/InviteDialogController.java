/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
public class InviteDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PlayerFunctions playerfunc = new PlayerFunctions();
    
    @FXML
    private Label usrName;
    
    int p1 , p2 ;
    
    @FXML
    private JFXButton accept;
    

    @FXML
    private JFXButton decline;
    

    ControlButtonsController invite=null;
    
    public void setControlObject(ControlButtonsController obj){
        invite=obj;
    }
    
    public void setUserName(String name){
        usrName.setText(name);
    }
//    public void accept()
//    {   
//       // MainGUI.primaryStage.close();
//        System.out.println("habllllll =======================");
        
    @FXML
    void decline(ActionEvent event)
    {
        ControlButtonsController.newStage.close();
        playerfunc.declineInvitation(p1, p2);
    }
    
    
    @FXML
    void accept(ActionEvent event) {
       ControlButtonsController.newStage.close();
       playerfunc.acceptinvitation(p1, p2);
       invite.loadBoard(false);
      //swaped
    }
    
    public void setplayersId(int p1,int p2)
    {
        this.p1=p1;
        this.p2=p2;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
