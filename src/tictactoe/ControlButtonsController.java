/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Server.ServerControl;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import Server.ServerControl;
import java.util.Vector;


public class ControlButtonsController implements Initializable {
    
    ObservableList li =FXCollections.observableArrayList();
   @FXML
    void invite_friend(ActionEvent event) {
        System.out.println("invite a friend!");
    }
    
    @FXML
    private ListView<?> listView;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        System.out.println("bateeeeeeee5");        
        
        for (Player p : PlayerFunctions.users){
            
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
            
            VBox userName_Score = new VBox();
            userName_Score.setSpacing(2);
            Text t = new Text(p.getUser_name());

            HBox score_button = new HBox();

            score_button.setSpacing(10);
            score_button.setAlignment(Pos.CENTER);

            Text score = new Text(String.valueOf(p.getScore()));

            Button invite = new Button();

            invite.setText("Invite");
            invite.setCursor(Cursor.HAND);

            invite.setStyle("-fx-color: #00FF00; -fx-border-width: 5px;");


            score_button.getChildren().addAll(score,invite);



            userName_Score.getChildren().addAll(t,score_button);

            item.getChildren().add(userName_Score);
            
            li.add(item);
        }
        listView.getItems().addAll(li);
    }    
    
}
