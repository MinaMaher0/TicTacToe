/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;


public class SignUpController implements Initializable {
   @FXML
    private JFXTextField userName;

    @FXML
    private JFXPasswordField password;

    @FXML
    private JFXTextField email;
    
    @FXML
    void sign_up_method(ActionEvent event)
    {
        String username = userName.getText();
        String emailAdress = email.getText();
         String passwordP = password.getText();
         PlayerFunctions p= new PlayerFunctions();
         System.out.println(p.signUp(username, emailAdress, passwordP));
        
    }
    
    @FXML
    void sign_up(ActionEvent event) {
        sign_up_method(event);
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ControlButtons.fxml"));
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("SignUp");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignUpController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
  
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
    }    
    
}
