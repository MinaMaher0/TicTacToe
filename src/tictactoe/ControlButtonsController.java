/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Server.ServerControl;
import ServerGui.*;
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
import java.io.IOException;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Modality;
import javafx.stage.Stage;


public class ControlButtonsController implements Initializable {
    public static Stage newStage = new Stage();
    PlayerFunctions pF;
    String s= new String("test");
    ObservableList li =FXCollections.observableArrayList();
    @FXML
    void invite_friend(ActionEvent event) {
        System.out.println("invite a friend!");
    }
    
    public boolean showInviteDialog(String name,int p1,int p2){
        boolean ret=false;
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("InviteDialog.fxml"));
            Parent root;
            root = (Parent)loader.load();
            InviteDialogController inviterController=loader.getController();
            inviterController.setUserName(name);
            inviterController.setplayersId(p1, p2);
            inviterController.setControlObject(this);
            newStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            newStage.setTitle("InviteDialogController");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ret;
    }
    public void showServerDownDialog()
    {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("ServerDownDialog.fxml"));
            Parent root;
            //Stage s= new Stage();
            root = (Parent)loader.load();
            newStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            newStage.setTitle("ServerFallen");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
            
        } catch (IOException ex) {
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @FXML
    private ListView<?> listView;
    
    public void setPlayerObj(PlayerFunctions obj){
        pF=obj;
        pF.setControlButtonsController(this);
    }
    
    public void showPlayers(){
        listView.getItems().clear();
        li.clear();
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
            
            invite.addEventHandler(ActionEvent.ACTION,new EventHandler<ActionEvent>() {
                @Override
                public void handle(ActionEvent event) {
                    int id = p.getId();
                    System.out.println(id);
                    pF.invitePlayer(id);
                }
            });
            
            if (p.getFlag()){
                invite.setText("Invite");
                invite.setCursor(Cursor.HAND);
                invite.setStyle("-fx-color: #00FF00; -fx-border-width: 5px;");
                
            }else {
                invite.setText("Offline");
                invite.setStyle("-fx-color: #A9A9A9; -fx-border-width: 5px;");
            }
                

            score_button.getChildren().addAll(score,invite);



            userName_Score.getChildren().addAll(t,score_button);

            item.getChildren().add(userName_Score);
            
            li.add(item);
        }
        listView.getItems().addAll(li);
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPlayers();
    }
    public void loadBoard(){
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TheBoard.fxml"));
            Parent root;
            root = (Parent)loader.load();
            TheBoardController boardObj=loader.getController();
            boardObj.SetPlayerFunctionObj(pF);
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("GameBoard");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show(); 
        } catch (Exception ex) {
            System.out.println(ex.toString());
Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void loadDeclineboard(String name){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("DeclineDialog.fxml"));
            Parent root;

            root = (Parent)loader.load();
            DeclineDialogController declineController=loader.getController();
            declineController.setUserName(name);
            newStage.initModality(Modality.WINDOW_MODAL);
            Scene scene=new Scene(root);
            newStage.setTitle("DeclineDialogController");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
