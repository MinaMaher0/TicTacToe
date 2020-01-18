package tictactoe;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
/**
 *  
 * @author Salamamina
 */
public class MainGUI extends Application {    
   public static Stage primaryStage;
    Parent root;
    @Override
    public void start(Stage stage) {
        try {   
            primaryStage=stage;
            root=FXMLLoader.load(getClass().getResource("SignIn.fxml"));
            Scene scene=new Scene(root);
            stage.setTitle("SignIn");
            stage.setScene(scene);
            stage.show();
            stage.setResizable(true);
        } catch (IOException ex) {
            Logger.getLogger(MainGUI.class.getName()).log(Level.SEVERE, null, ex);
        }
     
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
    
}
