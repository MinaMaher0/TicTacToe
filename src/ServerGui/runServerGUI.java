/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerGui;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


/**
 *
 * @author DELL
 */
public class runServerGUI extends Application{

    public static Stage primaryStage;
    Parent root;
    
    @Override 
    public void start(Stage stage){
       try{
            primaryStage=stage;
            root=FXMLLoader.load(getClass().getResource("ServerHome.fxml"));
            Scene scene=new Scene(root);
            stage.setTitle("AdminLogin");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(false);
        } catch (IOException ex) {
            Logger.getLogger(runServerGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
      public static void main(String[] args) {
        launch(args);
    }
}
