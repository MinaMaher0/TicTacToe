/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.ImageView;
import javafx.scene.image.Image;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author Me5a
 */
public class TestController implements Initializable {
    ObservableList li =FXCollections.observableArrayList();
    /**
     * Initializes the controller class.
     */
    
     @FXML
    private ListView<?> lis2;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        try {
           
        }catch(Exception ex){
            System.out.println("bate55555");
        }
        
        HBox item = new HBox();
        
        item.setSpacing(10);
        
        Image img = new Image("file:///C:/Users/Me5a/Desktop/Java project/TicTacToe/src/Images/1.jpg");
        ImageView imageView = new ImageView(img);
        imageView.setFitWidth(40);
        imageView.setFitHeight(40);
        Rectangle clip = new Rectangle(40,40);
        clip.setArcHeight(40);
        clip.setArcWidth(40);
        imageView.setClip(clip);
            
        item.getChildren().add(imageView);
                
        VBox v = new VBox();
        v.setSpacing(2);
        Text t = new Text("Mina Maher");
        
        HBox s = new HBox();
        
        s.setSpacing(10);
        s.setAlignment(Pos.CENTER);
        
        Text score = new Text("520");
                
        Button inv = new Button();
        
        inv.setText("Invite");
        inv.setCursor(Cursor.HAND);
        
        inv.setStyle("-fx-color: #00FF00; -fx-border-width: 5px;");
        
        
        s.getChildren().addAll(score,inv);
        
        
        
        v.getChildren().addAll(t,s);
        
        item.getChildren().add(v);
        li.removeAll(li);
        li.add(item);
        
        lis2.getItems().addAll(li);
    }    
    
}
