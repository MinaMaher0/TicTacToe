/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import com.jfoenix.controls.JFXTextArea;
import com.jfoenix.controls.JFXTextField;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;


import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author Salama
 */
public class TheBoardController implements Initializable {

    /**
     * Initializes the controller class.
     */
    PlayerFunctions pf ;
    
    
    public  void SetPlayerFunctionObj(PlayerFunctions obj)
    {
        pf=obj;
        pf.setBoardObj(this);
    }
   
    @FXML
    private Label num_of_tie;
    @FXML
    private Label playerOname;   
    @FXML
    private Label playerOscore;
    @FXML
    private Label playerTname;
    @FXML
    private Label pTscore;
    @FXML
    private Pane chat_pane;
    @FXML
    private JFXTextArea textArea;
    
    @FXML
    private JFXTextField textfield;
    @FXML
    private Button msgbtn;
    
    @FXML
    private Label lbl_13;
    
    @FXML
    private Label lbl_12;

    @FXML
    private Label lbl_11;

    @FXML
    private Label lbl_21;

    @FXML
    private Label lbl_22;

    @FXML
    private Label lbl_23;

    @FXML
    private Label lbl_31;

    @FXML
    private Label lbl_32;

    @FXML
    private Label lbl_33;
    
    public void setLbl(int cellNum,char cellChar)
    {
        switch(cellNum)
        {
            case 1:
                lbl_11.setText(String.valueOf(cellChar));
                
                break;
            
            case 2:
                lbl_12.setText(String.valueOf(cellChar));
                break;
           
            case 3:
                lbl_13.setText(String.valueOf(cellChar));
                break;
            
            case 4:
                lbl_21.setText(String.valueOf(cellChar));
                break;
                
            case 5:
                lbl_22.setText(String.valueOf(cellChar));
                break;
            
            case 6:
                lbl_23.setText(String.valueOf(cellChar));
                break;
           
            case 7:
                lbl_31.setText(String.valueOf(cellChar));
                break;
            
            case 8:
                lbl_32.setText(String.valueOf(cellChar));
                break;
                
            case 9:
                lbl_33.setText(String.valueOf(cellChar));
                break;
        }
    }
    
    public void sendLblRequest(int id)
    {
        pf.sendPlayedCellRequest(id);
    }
    

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        lbl_11.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(1);
               // lbl_11.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        
        lbl_12.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(2);
                //lbl_12.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        
        lbl_13.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(3);
                //lbl_13.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        
        lbl_21.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(4);
                //lbl_21.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        lbl_22.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(5);
                //lbl_22.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        lbl_23.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(6);
                //lbl_23.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        lbl_31.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(7);
                //lbl_31.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        lbl_32.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(8);
                //lbl_32.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
        lbl_33.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                sendLblRequest(9);
                //lbl_33.removeEventHandler(MouseEvent.MOUSE_CLICKED, this);
            }
        }
        );
    }    

    @FXML
    void lbl1(MouseEvent event) {
        sendLblRequest(1);
    }
     @FXML
    void lbl2(MouseEvent event) {
         sendLblRequest(2);

    }

    @FXML
    void lbl3(MouseEvent event) {
        sendLblRequest(3);

    }

    @FXML
    void lbl4(MouseEvent event) {
        sendLblRequest(4);

    }

    @FXML
    void lbl5(MouseEvent event) {
        sendLblRequest(5);

    }

    @FXML
    void lbl6(MouseEvent event) {
        sendLblRequest(6);

    }

    @FXML
    void lbl7(MouseEvent event) {
        sendLblRequest(7);

    }

    @FXML
    void lbl8(MouseEvent event) {
        sendLblRequest(8);

    }

    @FXML
    void lbl9(MouseEvent event) {
        sendLblRequest(9);

    }
       
       
    @FXML
    void textFtotextArea(ActionEvent event) {
       String theMessage=new String();
       theMessage=textfield.getText();
       pf.sendMessage(theMessage);
      textfield.setText("");
      
    }
   
    @FXML
    private Button chat_btn;
    
     @FXML
    void show_pane(ActionEvent event) {
        chat_pane.setVisible(true);
    }
     @FXML
    void close_window(ActionEvent event) {
        chat_pane.setVisible(false);
    }

   
  public void appendText(String body){
      if(textArea.getText() != ""){
      chat_pane.setVisible(true);
      textArea.appendText(body+"\n");
        // chat_pane.setVisible(false);
      }else{
          chat_pane.setVisible(false);
     // textArea.appendText(body+"\n");
     
  }
//    @Override
//    public void initialize(URL url, ResourceBundle rb) {
//        // TODO
//    }
  }

    
}
