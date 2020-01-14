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
import Server.ServerControl;
import Server.ServerSideClass;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXToggleButton;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
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
    private JFXListView <?> offlineP; 
    @FXML
    private JFXListView <?> onlineP; 
    
    @FXML
    private JFXToggleButton toggle;
    
    ObservableList on = FXCollections.observableArrayList();
    ObservableList off = FXCollections.observableArrayList();
    ServerControl serverControl; 
    int flag;
  
    @FXML
    void startServer(ActionEvent event) {
       /* serverControl.startServer();
        setOnlineList();*/
       if(toggle.isArmed())
        {
            if(flag == 0)
            {
                serverControl.startServer();
                setOnlineList();
                flag=1;
            }
            else
            {
             serverControl.stopServer();
             flag=0;
            }
        }
    }
    
    public void setOnlineList()
    {
        Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        offlineP.getItems().clear();
                        onlineP.getItems().clear();
                        on.clear();
                        off.clear();
                        for (Player p:ServerControl.players)
                        {
                            if(p.getFlag())
                                on.add(p.getUser_name());
                            else
                                off.add(p.getUser_name());
                        }
                        offlineP.getItems().addAll(off);
                        onlineP.getItems().addAll(on);  
                    }
                });
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       serverControl=new ServerControl();
       flag=0;
       
    }  
    
    
}
