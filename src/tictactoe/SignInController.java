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
    private JFXTextField userName;

    @FXML
    private JFXPasswordField password;

    @FXML
    void sign_in(ActionEvent event) {
        System.out.println(userName.getText());
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
