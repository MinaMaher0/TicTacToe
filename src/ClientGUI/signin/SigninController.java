/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import static clientgui.ClientGUI.myStage;
import java.io.IOException;
import java.net.URI;
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
import javafx.stage.Stage;
import clientgui.ClientGUI;
/**
 * FXML Controller class
 *
 * @author Salama
 */
public class SigninController implements Initializable {
   
   
    @FXML 
    private void signIn(ActionEvent event) //to goto control buttons scene
    {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("controlbuttons/ControlButton.fxml"));
            Scene scene = new Scene(root);
            ClientGUI.myStage.setTitle("Signin");
            ClientGUI.myStage.setScene(scene);
            ClientGUI.myStage.show();
            
            
            System.out.println("SignIn");
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //To goto signup scene
    @FXML 
    private void signUp(ActionEvent event)
    {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("signup/SignUp.fxml"));
            Scene scene = new Scene(root);
            ClientGUI.myStage.setTitle("Signin");
            ClientGUI.myStage.setScene(scene);
            ClientGUI.myStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SigninController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
