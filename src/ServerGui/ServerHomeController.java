/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import Server.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.SelectionMode;
import tictactoe.Player;

/**
 * FXML Controller class
 *
 * @author DELL
 */
public class ServerHomeController implements Initializable {

    /**
     * Initializes the controller class.
     */
    
    @FXML
    ListView <?> offlineP; 
    
    
    
    @FXML
      void startServer(ActionEvent event) {
          
        offlineP.getItems().addAll(ServerControl.offlineItems);
        offlineP.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        System.out.println("hllo");
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        //new ServerControl();
    }    
    
}
