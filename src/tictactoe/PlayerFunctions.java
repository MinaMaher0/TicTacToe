/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Server.ServerControl;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.application.Platform;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.paint.Paint;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.util.Duration;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import static tictactoe.ControlButtonsController.newStage;
import tray.animations.AnimationType;
import tray.notification.NotificationType;
import tray.notification.TrayNotification;
import utils.Request;

/**
 *
 * @author Aya Morsi
 */
public class PlayerFunctions implements Client {

    String str;
    DataInputStream input;
    PrintStream output;
    Socket s;
    Player pla = new Player();
    Boolean playerIsTurn = false;
    Game game=null;
    ControlButtonsController cbController = null;
    SignInController siginObj = null;
    SignUpController signupObj = null;
    TheBoardController boardObj = null;

    String messageContent = new String();

    public void setControlButtonsController(ControlButtonsController obj) {
        cbController = obj;
        cbController.showPlayers();
    }

    public void setSignUpObject(SignUpController obj) {
        signupObj = obj;
    }

    public void setSignInObject(SignInController obj) {
        siginObj = obj;
    }

    public void setBoardObj(TheBoardController obj) {
        boardObj = obj;
    }

    public static Vector<Player> users;

    public PlayerFunctions() {
        try {

            s = new Socket("127.0.0.1", 8000);

            input = new DataInputStream(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            users = new Vector<>();

        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        Thread th = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (input != null) {
                        try {
                            str = input.readLine();
                            System.out.println(str);
                            RequestHandller(str);
                            System.out.println("server response: " + str);
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
                        System.out.println("connection lost");
                        break;
                    }
                }
            }
        });
        th.start();
    }

    @Override
    public boolean connect() {
        return true;
    }

    @Override
    public boolean signIn(String email, String password) {
        try {
            JSONObject SigninObject = new JSONObject();
            SigninObject.put("email", email);
            SigninObject.put("password", password);
            SigninObject.put("RequestType", Request.LOGIN);
            output.println(SigninObject.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean signUp(String userName, String email, String password) {
        try {
            JSONObject SignupObject = new JSONObject();
            SignupObject.put("userName", userName);
            SignupObject.put("email", email);
            SignupObject.put("password", password);
            SignupObject.put("RequestType", Request.SIGNUP);
            output.println(SignupObject.toString());

        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public Vector<Player> getPlayers() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void printPlayer(Player p) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void playWithComputer() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean invitePlayer(int id) {
        JSONObject invitePlayerObject = new JSONObject();
        try {

            invitePlayerObject.put("senderID", pla.getId());
            invitePlayerObject.put("receiverID", id);
            invitePlayerObject.put("senderUserName", pla.getUser_name());
            invitePlayerObject.put("RequestType", Request.INVITE_PLAYER);

            output.println(invitePlayerObject.toString());
            //acceptinvitation(pla.getId(), id);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public void logOut(int pId) {

        try {
            JSONObject logOutObject = new JSONObject();
            try {
                logOutObject.put("userId", pId);
                logOutObject.put("RequestType", Request.LOG_OUT);
            } catch (JSONException ex) {
                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void RequestHandller(String str) {
        System.out.println("batata " + str);
        try {
            final JSONObject ReqObj = new JSONObject(str);
            switch (ReqObj.get("RequestType").hashCode()) {
                case Request.SIGN_UP_SUCCESS:
                    signupObj.SignUp_Success();
                    break;
                case Request.SIGN_UP_FAILED:
                    System.out.println("unsecss");
                    break;
                case Request.LOGIN_SUCCESS:
                    siginObj.sign_in_sucess();
                    pla.setId(ReqObj.getInt("id"));
                    pla.setEmail(ReqObj.getString("email"));
                    pla.setUser_name(ReqObj.getString("userName"));
                    pla.setScore(ReqObj.getInt("score"));
                    break;
                case Request.LOGIN_FAILED:
                  
                    siginObj.sign_in_faild();
                   
                    break;
                case Request.INVITE_PLAYER_SUCESS:
                    System.out.println("invitation accepted");
                    break;
                case Request.INVITE_PLAYER_FAILED:
                    System.out.println("invitation decliend");
                    break;

                case Request.INVITE_PLAYER:
                    if (cbController != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    System.out.println("user " + ReqObj);
                                    cbController.showInviteDialog(ReqObj.getString("senderUserName"), ReqObj.getInt("senderID"), pla.getId());
                                } catch (JSONException ex) {
                                    Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                    break;
                case Request.USERS:
                    users.clear();
                    JSONArray jArr = ReqObj.getJSONArray("users");
                    for (int i = 0; i < jArr.length(); ++i) {
                        Player p = convertJsonObjtoPlayer(jArr.getJSONObject(i));
                        users.add(p);
                    }
                    if (cbController != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                cbController.showPlayers();
                            }
                        });
                    }
                    break;

                case Request.START_GAME:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            if (cbController != null) {
                                cbController.loadBoard(false);
                                Platform.runLater(() -> {
                                    try {
                                        boardObj.exitDialog();
                                        boardObj.setTurnLbl(playerIsTurn);
                                        boardObj.setGameDetails(ReqObj.getString("playerOneName"),ReqObj.getInt("playerOneScore"),ReqObj.getString("playerTwoName"),ReqObj.getInt("playerTwoScore"),ReqObj.getInt("tieScore"));
                                    } catch (JSONException ex) {
                                        Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
                            } else {
                                System.out.println("nullllllllllll");
                            }
                        }
                    });
                    break;
                case Request.PLAYED_CELL:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                boardObj.setLbl(ReqObj.getInt("cellNum"), ReqObj.getString("cellChar").charAt(0));
                            } catch (JSONException ex) {
                                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    break;
                case Request.REFUSE_INVITATION:
                    cbController.loadDeclineboard(ReqObj.getString("usrName"));
                    break;
                case Request.SERVER_FAILED:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            System.out.println("Server Fallen ya beeh ");
                            cbController.showServerDownDialog();
                        }
                    });
                    break;
                case Request.PLAYER_TURN:
                    playerIsTurn = true;
                    System.out.println("yyyyyyyyyyyyyyy");
                    Platform.runLater(() -> {
                            boardObj.setTurnLbl(playerIsTurn);
                    });
                    break;
                case Request.SEND_MESSAGE:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                boardObj.appendText(ReqObj.getString("Message"));
                            } catch (JSONException ex) {
                                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                            }
                        }
                    });
                    break;
                case Request.PLAY_AGAIN:
                    showPlayAgain(ReqObj.getString("Message"),ReqObj.getString("Color"));
                    break;
                case Request.EXIT_GAME:
                    extiPlayAgain();
                    break;
                case Request.NOTIFICATION:
                    if (ReqObj.getInt("pID")!=pla.getId()){
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    notifi(ReqObj.getString("userName"));
                                } catch (JSONException ex) {
                                    Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                        
                    }
                    break;
            }

        } catch (Exception ex) {
            //System.out.println("bateeeeeeeeee5");
            System.out.println(ex.toString());
            //Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
    void notifi(String userName){
        NotificationType notification = NotificationType.SUCCESS;
        TrayNotification tray = new TrayNotification();
        tray.setTitle("New Player is Online");
        tray.setMessage(userName + " is Online now");
        tray.setRectangleFill(Paint.valueOf("#2A9A84"));
        tray.setAnimationType(AnimationType.POPUP);
        tray.showAndDismiss(Duration.seconds(2));
    }

    Player convertJsonObjtoPlayer(JSONObject jObj) {
        Player p = new Player();
        try {
            p.setEmail(jObj.getString("email"));
            p.setId(jObj.getInt("id"));
            p.setUser_name(jObj.getString("user_name"));
            p.setFlag(jObj.getBoolean("flag"));
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    public static void main(String[] args) {
        new PlayerFunctions();

    }

    @Override
    public boolean acceptinvitation(int pOneId, int pTwoId) {

        JSONObject acceptinvitation = new JSONObject();
        try {
            acceptinvitation.put("senderID", pOneId);
            acceptinvitation.put("receiverID", pTwoId);
            acceptinvitation.put("RequestType", Request.ACCEPT_INVITATION);
            output.println(acceptinvitation.toString());

        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void playWithComuter(String level) {
        game = new Game(pla, true, level);
        playerIsTurn = true;
        boardObj.setGameDetails(game.getPlayer1().getUser_name(),game.getFp_score(),"Computer", game.getSp_score(), game.getTie_score());
        boardObj.hideChatAndSave();
    }

    public void sendPlayedCellRequest(int cellNum, boolean isComputer) {
        System.out.println("turn " + playerIsTurn);
        if (!playerIsTurn) {
            // handle when it's not your turn
            return;
        } else {
            if (isComputer) {
                action(cellNum);
            } else {
                JSONObject json = new JSONObject();
                try {
                    json.put("RequestType", Request.PLAYED_CELL);
                    json.put("Player1ID", pla.getId());
                    json.put("cellNum", cellNum);
                    System.out.println(json);
                    output.println(json.toString());
                    playerIsTurn = false;
                    Platform.runLater(() -> {
                        boardObj.setTurnLbl(playerIsTurn);
                    });
                } catch (JSONException ex) {
                    Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void sort(Vector<Player> p) {

    }

    @Override
    public void declineInvitation(int pOneId, int pTwoId) {
        System.out.println("ayaaaaaaaaaa");
        JSONObject declineinvitation = new JSONObject();
        try {
            declineinvitation.put("SenderId", pOneId);
            declineinvitation.put("RecieverId", pTwoId);
            declineinvitation.put("RequestType", Request.REFUSE_INVITATION);
            output.println(declineinvitation.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendMessage(String msg) {
        try {
            JSONObject sendMsg = new JSONObject();
            sendMsg.put("RequestType", Request.SEND_MESSAGE);
            //sendMsg.put("PlayerID", pla.getId());
            sendMsg.put("Message", msg);
            output.println(sendMsg.toString());

        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    void showPlayAgain(String msg,String color) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boardObj.showPlayAgainDialog(msg,color);
            }
        });
    }
    
    void extiPlayAgain(){
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boardObj.exitPlayAgain();
            }
        });
    }

    public void playAgain() {
        if (game!=null) {
            game.playAgain();
            boardObj.setGameDetails(game.getPlayer1().getUser_name(),game.getFp_score(),"Computer", game.getSp_score(), game.getTie_score());
            if (game.playerTurn==-1)
                computerTurn();
        }else {
            JSONObject json = new JSONObject();
            try {
                json.put("RequestType", Request.PLAY_AGAIN);
                json.put("ID", pla.getId());
                output.println(json.toString());
            } catch (JSONException ex) {
                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void exitGame() {
        if (game!=null)
            game = null;
        else{
            JSONObject json = new JSONObject();
            try {
                json.put("RequestType", Request.EXIT_GAME);
                output.println(json.toString());
            } catch (JSONException ex) {
                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    int playerTurn(int cellNum) {
        char ch = game.getPlayerChar();
        Platform.runLater(() -> {
            boardObj.setLbl(cellNum, ch);
        });
        return game.chooseCell(cellNum - 1);
    }

    int computerTurn() {
        int cpuCell;
        if (game.getLevel().equals("hard")) {
            cpuCell = PlayWithComputer.hard(game.getBoard());
        } else if (game.getLevel().equals("medium")) {
            cpuCell = PlayWithComputer.medium(game.getBoard());
        } else {
            cpuCell = PlayWithComputer.easy(game.getBoard());
        }
        char ch = game.getPlayerChar();
        Platform.runLater(() -> {
            boardObj.setLbl(cpuCell + 1, ch);
        });
        return game.chooseCell(cpuCell);
    }

    void action(int cellNum) {
        if (!game.isAvailable(cellNum - 1)) {
            return;
        }
        int ret = playerTurn(cellNum);
        if (ret == 1) {
            showPlayAgain("you win","Green");
            //boardObj.setTurnLbl(false);
        } else if (ret == -1) {
            showPlayAgain("Tie","Yellow");
        } else {
            Platform.runLater(() -> {
                boardObj.setTurnLbl(!playerIsTurn);
            });
            int cpuret = computerTurn();
            if (cpuret == 1) {
                showPlayAgain("You Lose","Red");
            } else if (cpuret == -1) {
                showPlayAgain("Tie","Yellow");
            } else {
                Platform.runLater(() -> {
                    boardObj.setTurnLbl(playerIsTurn);
                });
            }
        }
    }
    
    public boolean isPlayWithComputer(){
        return game!=null;
    }
    
}
