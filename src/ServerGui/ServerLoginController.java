/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGui;

import Server.*;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
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
import javafx.stage.Stage;

import javafx.scene.Scene;
import tictactoe.PlayerFunctions;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ServerLoginController implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private JFXTextField username;

    @FXML
    private JFXPasswordField password;
    
    @FXML 
    void signIn(ActionEvent event) //to goto control buttons scene
    {  
        String serverUserName = username.getText();
        String serverPassword = password.getText();
    
        if(serverUserName.equals(ServerSideClass.getName()) 
                &&serverPassword.equals(ServerSideClass.getPassword()))
        {
            System.out.println("Hello Server");
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
