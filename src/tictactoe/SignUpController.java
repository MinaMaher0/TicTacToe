/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;
import javafx.scene.control.Label;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Background;
import javafx.scene.layout.BorderPane;
import javafx.scene.paint.Color;
import org.apache.commons.validator.routines.EmailValidator;


public class SignUpController implements Initializable {
    
PlayerFunctions p= new PlayerFunctions();
    @FXML
    private JFXTextField userName;
    @FXML
    private JFXPasswordField password;
    @FXML
    private JFXTextField email;
    @FXML
    private Label userReq;
    @FXML
    private Label passwordReq;
    @FXML
    private Label emailReq;
    @FXML
    private Label hasAccount;
    
    @FXML
    private BorderPane borderPane1;

    @FXML
    private BorderPane borderPane2;

    @FXML
    private ImageView anger;

    @FXML
    private BorderPane borderPane4;

    @FXML
    private BorderPane borderPane3;

    @FXML
    private BorderPane borderPane5;
    
    void imgBackgroundColor(int i){
        borderPane1.setStyle(i==1? "-fx-background-color: #0000FF;":"-fx-background-color: #FFFFFF;");
        borderPane2.setStyle(i==2? "-fx-background-color: #0000FF;":"-fx-background-color: #FFFFFF;");
        borderPane3.setStyle(i==3? "-fx-background-color: #0000FF;":"-fx-background-color: #FFFFFF;");
        borderPane4.setStyle(i==4? "-fx-background-color: #0000FF;":"-fx-background-color: #FFFFFF;");
        borderPane5.setStyle(i==5? "-fx-background-color: #0000FF;":"-fx-background-color: #FFFFFF;");
    }
    
      @FXML
    void pic1(MouseEvent event) {
         Image = "5.png";
         imgBackgroundColor(1);
    }

    @FXML
    void pic2(MouseEvent event) {
        Image = "4.png";
        imgBackgroundColor(2);
    }

    @FXML
    void pic3(MouseEvent event) {
        Image = "2.png";
        imgBackgroundColor(3);
    }

    @FXML
    void pic4(MouseEvent event) {
         Image = "1.png";
         imgBackgroundColor(4);
    }

    @FXML
    void pic5(MouseEvent event) {
        Image = "3.png";
        imgBackgroundColor(5);
    }


   String username = new String();
   String emailAdress = new String();
   String passwordP = new String();
   String Image = new String("1.png");
     
    boolean SignUpValidate() {
          username = userName.getText();
          emailAdress = email.getText();
          passwordP = password.getText();

        String pattern = "^[0-9]{6,}$";
        if (username.equals("")) {
            userReq.setVisible(true);
            return false;
        }
        if (emailAdress.equals("")) {
            emailReq.setVisible(true);
            return false;
        }
        if (passwordP.equals("")) {
            passwordReq.setVisible(true);
            return false;
        }
        if (!EmailValidator.getInstance().isValid(emailAdress)) {
            email.setText("Enter a valid email address");
            return false;
        }
        if (!passwordP.matches(pattern)) {
            passwordReq.setVisible(true);
            return false;
        }
        return true;
    }
    
    @FXML
    void sign_up(ActionEvent event) {
        
        if(!SignUpValidate())
            return;
        PlayerFunctions p = new PlayerFunctions();
        p.setSignUpObject(this);
        p.signUp(username, emailAdress, passwordP ,Image);
    }
    
    void SignUp_Success()
    {
             try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("ControlButtons.fxml"));
                    Parent root;
                    root = (Parent)loader.load();
                    ControlButtonsController CBController=loader.getController();
                    CBController.setPlayerObj(p);
                    Scene scene = new Scene(root);
                    MainGUI.primaryStage.setTitle("SignUp");
                    MainGUI.primaryStage.setScene(scene);
                    MainGUI.primaryStage.show(); 
        } catch (IOException ex) {
            Logger.getLogger(SignInController.class.getName()).log(Level.SEVERE, null, ex);
         } 
    }
    

    void sign_Up_failed(){
       hasAccount.setVisible(true);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

}
