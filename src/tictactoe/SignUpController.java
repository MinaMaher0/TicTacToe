/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
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
import org.apache.commons.validator.routines.EmailValidator;

public class SignUpController implements Initializable {
    

    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private Label urequired;

   String username = new String();
   String emailAdress = new String();
   String passwordP = new String();

     
    boolean SignUpValidate() {
          username = userName.getText();
          emailAdress = email.getText();
          passwordP = password.getText();

        String pattern = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
        if (username.equals("")) {
            urequired.setVisible(true);
            return false;
        }
        if (emailAdress.equals("")) {
            email.setText("entar your email");
            return false;
        }
        if (passwordP.equals("")) {
            password.setText("enter your password");
            return false;
        }
        if (!EmailValidator.getInstance().isValid(emailAdress)) {
            email.setText("Enter a valid email address");
            return false;
        }
        if (!passwordP.matches(pattern)) {
            System.out.println("a digit must occur at least once "
                    + "a lower case letter must occur at least once"
                    + "an upper case letter must occur at least once"
                    + "a special character must occur at least once"
                    + "no whitespace allowed in the entire string"
                    + "at least 8 characters");
            password.setText("lslls");
            return false;
        }
        return true;
    }
    
    @FXML
    void sign_up(ActionEvent event) {
  
       if(!SignUpValidate())
       {
           return;
       }
        PlayerFunctions p = new PlayerFunctions();
        p.signUp(username, emailAdress, passwordP);

        
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
