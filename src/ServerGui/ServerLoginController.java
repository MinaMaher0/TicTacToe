/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGui;


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
    private void signIn(ActionEvent event) //to goto control buttons scene
    {
        try {
            Parent root;
            root = FXMLLoader.load(getClass().getResource("controlbuttons/ControlButton.fxml"));
            Scene scene = new Scene(root);
            /*ClientGUI.myStage.setTitle("Signin");
            ClientGUI.myStage.setScene(scene);
            ClientGUI.myStage.show();*/
            System.out.println("Server SignIn");
        } catch (IOException ex) {
            Logger.getLogger(ServerLoginController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
