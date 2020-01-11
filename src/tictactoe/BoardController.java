/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import de.jensd.fx.glyphs.fontawesome.FontAwesomeIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIcon;
import de.jensd.fx.glyphs.materialdesignicons.MaterialDesignIconView;
import java.awt.Desktop.Action;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.Background;
import javafx.scene.layout.Pane;

public class BoardController implements Initializable {
    @FXML
    private Button chat_button;
    @FXML
    private Button close_pane;
    @FXML
    private Pane chat_pane;
    
    
    @FXML
    private Button btn23;

    @FXML
    private Button btn22;

    @FXML
    private Button btn21;

    @FXML
    private Button btn13;
    @FXML
    private Button btn12;
    @FXML
    private Button btn11;
    @FXML
    private Button btn33;
    @FXML
    private Button btn32;
    @FXML
    private Button btn31;
    @FXML
    private void chat_pane() {

        chat_pane.setVisible(true);
    }

    @FXML
    private void close_chat_pane() {
        chat_pane.setVisible(false);
    }
  @FXML
  private void set_x(){
      btn11.setText("X");
     
  }
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }

}
