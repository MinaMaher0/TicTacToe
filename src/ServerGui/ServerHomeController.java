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
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
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
    private Label serverCondition;
    
     ObservableList on = FXCollections.observableArrayList();
    ObservableList off = FXCollections.observableArrayList();
  ServerControl serverControl= new ServerControl();

    
    @FXML
    void startServer(ActionEvent event) {
        serverControl.startServer();
        setOnlineList();
        serverCondition.setText("Server is Running");
        serverCondition.setFont(Font.font(15));
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
    @FXML
    void stopServer(ActionEvent event) {
        serverControl.stopServer();
        serverCondition.setText("Server is Stopped");
        serverCondition.setFont(Font.font(15));
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
              }  
    
    
}
