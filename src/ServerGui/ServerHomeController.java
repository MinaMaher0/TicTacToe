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
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.SelectionMode;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.shape.Rectangle;
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
    
    ObservableList on = FXCollections.observableArrayList();
    ObservableList off = FXCollections.observableArrayList();
    
    @FXML
    void startServer(ActionEvent event) {
        new ServerControl().startServer();
        for(Player p:ServerControl.players)
        {
            if(p.getFlag() == true)
                on.add(Item(p.getUser_name()));
            else 
                off.add(Item(p.getUser_name()));
        }
        onlineP.getItems().addAll(on);
        offlineP.getItems().addAll(off);
        
    }
    @FXML
    void stopServer(ActionEvent event) {
        new ServerControl().stopServer();
    }
    public HBox Item(String name)
    {
        HBox item=new HBox();
        item.setSpacing(10);
        
        Image pP= new Image("1.jpg");
        ImageView imgView=new ImageView(pP);
        imgView.setFitWidth(40);
        imgView.setFitHeight(40);
        
        Rectangle circle= new Rectangle(40,40);
        circle.setArcWidth(40);
        circle.setArcHeight(40);
        
        imgView.setClip(circle);
        item.getChildren().add(imgView);
        
        Text playerName= new Text(name);
        
        item.getChildren().addAll(imgView,playerName);
        
        return item;
    }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    
}
