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
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.VPos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Modality;
import javafx.stage.Stage;



public class ControlButtonsController implements Initializable {
    public static Stage newStage = new Stage();
    PlayerFunctions pF;
    Player p = new Player();
    String s= new String("test");    
    ObservableList li =FXCollections.observableArrayList();
    ObservableList li2 = FXCollections.observableArrayList();
    ObservableList li3 = FXCollections.observableArrayList();
    @FXML
    void invite_friend(ActionEvent event) {
        System.out.println("invite a friend!");
    }
    @FXML
    private Label prof_uName;

    @FXML
    private Label prof_Score;
    
    @FXML
    void Exit(ActionEvent event){
        pF.logOut(p.getId());
    }
    
      @FXML
    void playWithComputer(ActionEvent event) {
        try {            
            FXMLLoader loader = new FXMLLoader(getClass().getResource("Levels.fxml"));
            Parent root;
            root = (Parent)loader.load();
            LevelsController levelsObj=loader.getController();
            levelsObj.setPlayerObj(pF);
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("GameBoard");
            MainGUI.primaryStage.setScene(scene); 
            MainGUI.primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
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
            root = (Parent)loader.load();
            ServerDownDialogController sDDControl= new ServerDownDialogController();
            sDDControl.setControlObject(this);
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
    
    @FXML
    private ListView<?> listView2;
    @FXML
    private ListView<?> listView3;
    public void showPlayers() {

        listView.getItems().clear();
        listView2.getItems().clear();
        listView3.getItems().clear();
        li.clear();
        li2.clear();
        li3.clear();
        for (Player p : PlayerFunctions.users) {
            if (pF != null && p.getId() == pF.getPlayer().getId()) { //for the signned in user profile
                String userName = new String();
                String score = new String();
                score = String.valueOf(pF.pla.getScore());
                userName = pF.getPlayer().getUser_name();
                prof_uName.setText(userName);
                prof_Score.setText(score);
                continue;
            }
            if (p.getScore() >= 100) {

                System.out.println("score = " + p.getProfile_picture());
                HBox item = new HBox();
                item.setSpacing(10);
                 Image img = new Image(getClass().getResource("../Images/"+p.getProfile_picture()).toString(), true);
            ImageView imageView = new ImageView(img);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                Rectangle clip = new Rectangle(40, 40);
                clip.setArcHeight(40);
                clip.setArcWidth(40);
                imageView.setClip(clip);
                item.getChildren().add(imageView);

                VBox userName_Score = new VBox();

                userName_Score.setSpacing(2);
                Text t = new Text(p.getUser_name());
                t.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                HBox score_button = new HBox();

                score_button.setSpacing(120);
                score_button.setAlignment(Pos.CENTER);

                Text score = new Text(String.valueOf(p.getScore()));
                score.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                Button invite = new Button();

                invite.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int id = p.getId();
                        System.out.println(id);
                        pF.invitePlayer(id);
                    }
                });

                if (p.getFlag()) {
                    invite.setText("Invite");
                    invite.setCursor(Cursor.HAND);
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 10px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill:#37cd0d;\n"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + " -fx-border-color:#00E676;"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");

                } else {
                    invite.setText("Offline");
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 8px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill: #756C6C;\n"
                            + " -fx-border-color:#756C6C;"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");
                }

                score_button.getChildren().addAll(score, invite);

                userName_Score.getChildren().addAll(t, score_button);

                item.getChildren().add(userName_Score);

                li.add(item);

            }
            if (p.getScore() < 100 && p.getScore() >= 50) {

                System.out.println("score = " + p.getScore());
                HBox item2 = new HBox();
                item2.setSpacing(10);
                Image img = new Image(getClass().getResource("../Images/"+p.getProfile_picture()).toString(), true);
            ImageView imageView = new ImageView(img);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                Rectangle clip = new Rectangle(40, 40);
                clip.setArcHeight(40);
                clip.setArcWidth(40);
                imageView.setClip(clip);
                item2.getChildren().add(imageView);

                VBox userName_Score = new VBox();

                userName_Score.setSpacing(2);
                Text t = new Text(p.getUser_name());
                t.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                HBox score_button = new HBox();

                score_button.setSpacing(120);
                score_button.setAlignment(Pos.CENTER);

                Text score = new Text(String.valueOf(p.getScore()));
                score.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                Button invite = new Button();

                invite.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int id = p.getId();
                        System.out.println(id);
                        pF.invitePlayer(id);
                    }
                });

                if (p.getFlag()) {
                    invite.setText("Invite");
                    invite.setCursor(Cursor.HAND);
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 10px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill:#37cd0d;\n"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + " -fx-border-color:#00E676;"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");

                } else {
                    invite.setText("Offline");
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 8px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill: #756C6C;\n"
                            + " -fx-border-color:#756C6C;"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");
                }

                score_button.getChildren().addAll(score, invite);

                userName_Score.getChildren().addAll(t, score_button);

                item2.getChildren().add(userName_Score);
                li2.add(item2);
            }
             if (p.getScore() <= 50) {

                System.out.println("score = " + p.getScore());
                HBox item3 = new HBox();
                item3.setSpacing(10);
                Image img = new Image(getClass().getResource("../Images/"+p.getProfile_picture()).toString(), true);
            ImageView imageView = new ImageView(img);
                imageView.setFitWidth(40);
                imageView.setFitHeight(40);
                Rectangle clip = new Rectangle(40, 40);
                clip.setArcHeight(40);
                clip.setArcWidth(40);
                imageView.setClip(clip);
                item3.getChildren().add(imageView);

                VBox userName_Score = new VBox();

                userName_Score.setSpacing(2);
                Text t = new Text(p.getUser_name());
                t.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                HBox score_button = new HBox();

                score_button.setSpacing(120);
                score_button.setAlignment(Pos.CENTER);

                Text score = new Text(String.valueOf(p.getScore()));
                score.setStyle(" -fx-font-family: 'Comic Sans MS';-fx-font-size : 15px;-fx-font: bold; -fx-text-fill: #756C6C;");
                Button invite = new Button();

                invite.addEventHandler(ActionEvent.ACTION, new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent event) {
                        int id = p.getId();
                        System.out.println(id);
                        pF.invitePlayer(id);
                    }
                });

                if (p.getFlag()) {
                    invite.setText("Invite");
                    invite.setCursor(Cursor.HAND);
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 10px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill:#37cd0d;\n"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + " -fx-border-color:#00E676;"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");

                } else {
                    invite.setText("Offline");
                    invite.setStyle(" -fx-outline: none;\n"
                            + "    -fx-transition: .5s;\n"
                            + "    -fx-font-size : 8px;\n"
                            + "    -fx-background-color: FFF;\n"
                            + "    -fx-text-fill: #756C6C;\n"
                            + " -fx-border-color:#756C6C;"
                            + "    -fx-font-weight:bold;\n"
                            + "    -fx-font-family: 'Comic Sans MS';\n"
                            + "    -fx-font: italic;\n"
                            + "-fx-border-width: 2px;"
                            + "   -fx-font: bold;");
                }

                score_button.getChildren().addAll(score, invite);

                userName_Score.getChildren().addAll(t, score_button);

                item3.getChildren().add(userName_Score);
                li3.add(item3);
            }
            
            
            
            
            

        }
        listView.getItems().addAll(li);
        listView2.getItems().addAll(li2);
        listView3.getItems().addAll(li3);

    }


    @Override
    public void initialize(URL url, ResourceBundle rb) {
        showPlayers();
    }
    
    public void loadBoard(boolean isComputer){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("TheBoard.fxml"));
            Parent root;
            root = (Parent)loader.load();
            TheBoardController boardObj=loader.getController();
            boardObj.SetPlayerFunctionObj(pF);
            boardObj.setComputerPlayer(isComputer);
            Scene scene = new Scene(root);
            MainGUI.primaryStage.setTitle("GameBoard");
            MainGUI.primaryStage.setScene(scene);
            MainGUI.primaryStage.show(); 
        } catch (Exception ex) {
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
            declineController.setControlObject(this);
            Scene scene=new Scene(root);
            newStage.setTitle("DeclineInvitation");
            newStage.setScene(scene);
            newStage.show();
            newStage.setResizable(false);
        } catch (Exception ex) {
            System.out.println(ex.toString());
            Logger.getLogger(ControlButtonsController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
