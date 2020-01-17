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
    
    @FXML
    private JFXListView <?> offlineP; 
    @FXML
    public JFXListView <?> onlineP; 
    
    @FXML
    private JFXToggleButton toggle;
   
    ObservableList off = FXCollections.observableArrayList();
    ObservableList on = FXCollections.observableArrayList();
    
    ServerControl serverControl; 
    
    int flag;
  
    @FXML
    void startServer(ActionEvent event) {
       if(toggle.isArmed())
        {
            if(flag == 0)
            {
                toggle.setText("Close Server");
                serverControl.startServer();
                setOfflineList();
                flag=1;
            }
            else
            {
                toggle.setText("Open Server");
                serverControl.stopServer();
                flag=0;
                on.clear();
                off.clear();
                offlineP.getItems().clear();
                onlineP.getItems().clear();
            }
        }
    }    
    public void setOfflineList()
    {
        on.clear();
        off.clear();
        offlineP.getItems().clear();
        onlineP.getItems().clear();
        for (Player p:ServerControl.players)
        {
            if(!p.getFlag())
                off.add(p.getUser_name()+"              "+p.getScore());
            else
                on.add(p.getUser_name()+"               "+p.getScore());
        }
        offlineP.getItems().addAll(off);
        onlineP.getItems().addAll(on);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
       serverControl=new ServerControl();
       serverControl.setHomeControllerObj(this);
       flag=0;
    }  
    
    
}
