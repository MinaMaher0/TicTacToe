/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGui;

import com.jfoenix.controls.JFXButton;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import tictactoe.ControlButtonsController;
import tictactoe.MainGUI;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ServerDownDialogController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    private JFXButton ok;
    
    ControlButtonsController sobj = null;
    public void setControlObject(ControlButtonsController obj){
        sobj=obj;
    }
    
    @FXML
    void accept(ActionEvent event) {
        ControlButtonsController.newStage.close();
        MainGUI.primaryStage.close();
        System.exit(0);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
