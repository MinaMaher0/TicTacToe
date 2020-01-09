/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;

public class ControlButtonsController implements Initializable {
   @FXML
    void invite_friend(ActionEvent event) {
        System.out.println("invite a friend!");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
    }    
    
}
