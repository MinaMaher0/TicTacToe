/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

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
                lbl_11.setText(String.valueOf(cellChar));
                break;
           
            case 3:
                lbl_11.setText(String.valueOf(cellChar));
                break;
            
            case 4:
                lbl_11.setText(String.valueOf(cellChar));
                break;
                
            case 5:
                lbl_11.setText(String.valueOf(cellChar));
                break;
            
            case 6:
                lbl_11.setText(String.valueOf(cellChar));
                break;
           
            case 7:
                lbl_11.setText(String.valueOf(cellChar));
                break;
            
            case 8:
                lbl_11.setText(String.valueOf(cellChar));
                break;
                
            case 9:
                lbl_11.setText(String.valueOf(cellChar));
                break;
        }
    }
    
    public void sendLblRequest(int id)
    {
        pf.sendPlayedCellRequest(id);
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
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
    
}
