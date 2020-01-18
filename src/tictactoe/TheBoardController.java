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
    boolean isComputer=false;
    EventHandler<Event> lbl_11Event;
    EventHandler<Event> lbl_12Event;
    EventHandler<Event> lbl_13Event;
    EventHandler<Event> lbl_21Event;
    EventHandler<Event> lbl_22Event;
    EventHandler<Event> lbl_23Event;
    EventHandler<Event> lbl_31Event;
    EventHandler<Event> lbl_32Event;
    EventHandler<Event> lbl_33Event;
    
    
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
    
    public void setComputerPlayer(boolean isComputer){
        this.isComputer=isComputer;
    }
    
    @FXML
    private Pane turn_lbl;
    @FXML
    private Label turnLbl;
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
                lbl_11.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_11Event);
                break;
            
            case 2:
                lbl_12.setText(String.valueOf(cellChar));
                lbl_12.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_12Event);
                break;
           
            case 3:
                lbl_13.setText(String.valueOf(cellChar));
                lbl_13.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_13Event);
                break;
            
            case 4:
                lbl_21.setText(String.valueOf(cellChar));
                lbl_21.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_21Event);
                break;
                
            case 5:
                lbl_22.setText(String.valueOf(cellChar));
                lbl_22.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_22Event);
                break;
            
            case 6:
                lbl_23.setText(String.valueOf(cellChar));
                lbl_23.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_23Event);
                break;
           
            case 7:
                lbl_31.setText(String.valueOf(cellChar));
                lbl_31.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_31Event);
                break;
            
            case 8:
                lbl_32.setText(String.valueOf(cellChar));
                lbl_32.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_32Event);
                break;
                
            case 9:
                lbl_33.setText(String.valueOf(cellChar));
                lbl_33.removeEventHandler(MouseEvent.MOUSE_CLICKED, lbl_33Event);
                break;
        }
    }
    
    public void setTurnLbl(boolean turn){
        turnLbl.setVisible(turn);
    }
    public void sendLblRequest(int id)
    {
        pf.sendPlayedCellRequest(id,isComputer);
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        
        lbl_11Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(1);
                }else{
                    
                }
            }
        };
        
        lbl_12Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(2);
                }else{
                    
                }
            }
        };
        
        lbl_13Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(3);
                }else{
                    
                }
            }
        };
        
        lbl_21Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(4);
                }else{
                    
                }
            }
        };
        
        lbl_22Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(5);
                }else{
                    
                }
            }
        };
        
        lbl_23Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(6);
                }else{
                    
                }
            }
        };
        
        lbl_31Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(7);
                }else{
                    
                }
            }
        };
        
        lbl_32Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(8);
                }else{
                    
                }
            }
        };
        
        lbl_33Event=new EventHandler<Event>() {
            @Override
            public void handle(Event event) {
                 if (turnLbl.isVisible()){
                    sendLblRequest(9);
                }else{
                    
                }
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


        };
        
        lbl_11.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_11Event);
        lbl_12.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_12Event);
        lbl_13.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_13Event);
        lbl_21.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_21Event);
        lbl_22.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_22Event);
        lbl_23.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_23Event);
        lbl_31.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_31Event);
        lbl_32.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_32Event);
        lbl_33.addEventHandler(MouseEvent.MOUSE_CLICKED, lbl_33Event);
    }
    
}
