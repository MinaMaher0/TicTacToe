/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package javafxdemo;

import com.sun.corba.se.pept.transport.EventHandler;
import com.sun.scenario.effect.impl.prism.PrImage;
import java.awt.event.ActionEvent;
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
 * @author Salama
 */
public class JavaFXDemo extends Application{

    Parent root;

    @Override
    public void start(Stage primaryStage) {

        try {
            root = FXMLLoader.load(getClass().getResource("Login.fxml"));

            Scene scene = new Scene(root);
            primaryStage.setTitle("Login");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException ex) {
            Logger.getLogger(JavaFXDemo.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
  
}
