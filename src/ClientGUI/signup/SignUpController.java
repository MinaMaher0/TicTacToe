/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package clientgui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import clientgui.ClientGUI;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class SignUpController implements Initializable {
    @FXML 
    private void signUp(ActionEvent event)
    {
         
            System.out.println("SIGNUP");
    }
     @FXML 
    private void goBackSignIn(ActionEvent event)
    {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("signin/Signin.fxml"));
            Scene scene = new Scene(root);
            ClientGUI.myStage.setTitle("Signin");
            ClientGUI.myStage.setScene(scene);
            ClientGUI.myStage.show();
         
            System.out.println("SIGNUP");
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    
    
}
