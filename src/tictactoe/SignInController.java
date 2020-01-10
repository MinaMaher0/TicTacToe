/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;


import com.jfoenix.controls.JFXPasswordField;
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
import com.jfoenix.controls.JFXTextField;
/**
 * FXML Controller class
 *
 * @author Salama
 */
public class SignInController implements Initializable {
    
   @FXML
    private JFXTextField email;

    @FXML
    private JFXPasswordField password;

    @FXML
    void sign_in(ActionEvent event) {
         String emailAdress = email.getText();
         String passwordP = password.getText();
         PlayerFunctions p= new PlayerFunctions();
         System.out.println(p.signIn(emailAdress, passwordP));
         
    }
    
     @FXML
    void tosign_up(ActionEvent event) {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("SignUp.fxml"));
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("SignUp");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    @FXML
    void controlButtons(ActionEvent event) {
        sign_in(event);
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("ControlButtons.fxml"));
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("Signin");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
        @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }   
    }

    

    
     
    
