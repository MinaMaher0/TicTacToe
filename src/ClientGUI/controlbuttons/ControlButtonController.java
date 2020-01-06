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
/**
 * FXML Controller class
 *
 * @author Salama
 */
public class ControlButtonController implements Initializable {
  
    @FXML 
    private void exit(ActionEvent event)
    {
            //System.out.println("invite a friend!");
            ClientGUI.myStage.close();
    }
    @FXML 
    private void invite(ActionEvent event)
    {
            System.out.println("invite a friend!");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
      
    }    
    
}
