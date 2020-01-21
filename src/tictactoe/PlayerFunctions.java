/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import Server.*;
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
    ServerSideClass sSC= new ServerSideClass();
    
    public void setControlButtonsController(ControlButtonsController obj){
        cbController=obj;
        cbController.showPlayers();
    }

    public void setSignUpObject(SignUpController object) {
        signupObj = object;
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
                            RequestHandller(str);
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    } else {
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
            SigninObject.put("RequestType", Request.LOGIN);
            SigninObject.put("email", email);
            SigninObject.put("password", password);
            output.println(SigninObject.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    @Override
    public boolean signUp(String userName, String email, String password ,String image) {
        try {
            JSONObject SignupObject = new JSONObject();
            SignupObject.put("RequestType", Request.SIGNUP);
            SignupObject.put("userName", userName);
            SignupObject.put("email", email);
            SignupObject.put("password", password);
            SignupObject.put("image",image);
            output.println(SignupObject.toString());
            
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
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
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }

        return true;
    }

    @Override
    public void logOut(int pId) {
        JSONObject logOutObject = new JSONObject();
        try {
            logOutObject.put("userId", pId);
            logOutObject.put("RequestType", Request.LOG_OUT);
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.exit(0);
    }

    public void RequestHandller(String str) {
        try {
            final JSONObject ReqObj = new JSONObject(str);
            switch (ReqObj.get("RequestType").hashCode()) {
                case Request.SIGN_UP_SUCCESS:
                    Platform.runLater(() -> {
                        signupObj.SignUp_Success();
                    });
                    break;
                case Request.SIGN_UP_FAILED:
                    Platform.runLater(() -> {
                        signupObj.sign_Up_failed();
                    });
                    break;
                case Request.LOGIN_SUCCESS:
                    Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                    siginObj.sign_in_sucess();
                                }
                            }
                        );
                        pla.setId(ReqObj.getInt("id"));
                        pla.setEmail(ReqObj.getString("email"));
                        pla.setUser_name(ReqObj.getString("userName"));
                        pla.setScore(ReqObj.getInt("score"));
                        pla.setProfile_picture(ReqObj.getString("pPic"));
                        break;
                case Request.LOGIN_FAILED:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                               siginObj.sign_in_faild();
                        }
                    });
                    break;
                case Request.INVITE_PLAYER:
                    if (cbController != null) {
                        Platform.runLater(new Runnable() {
                            @Override
                            public void run() {
                                try {
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
                            if (cbController!=null){
                                cbController.loadBoard(false);
                                Platform.runLater(() -> {
                                    try {
                                        boardObj.exitDialog();
                                        boardObj.setTurnLbl(playerIsTurn);
                                        boardObj.setGameDetails(ReqObj.getString("playerOneName"),ReqObj.getInt("playerOneScore"),ReqObj.getString("playerOnePic"),ReqObj.getString("playerTwoName"),ReqObj.getInt("playerTwoScore"),ReqObj.getString("playerTwoPic"),ReqObj.getInt("tieScore"),ReqObj.getString("GameBoard"));
                                    } catch (JSONException ex) {
                                        Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                                    }
                                });
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
                    Platform.runLater(() -> {
                        try {
                             cbController.loadDeclineboard(ReqObj.getString("userName"));
                        }catch (JSONException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    });
                    break;
                case Request.SERVER_FAILED:
                    Platform.runLater(new Runnable() {
                        @Override
                        public void run() {
                            cbController.showServerDownDialog();
                        }
                    });
                    break;
                case Request.PLAYER_TURN:
                    playerIsTurn = true;
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
                                    notifi(ReqObj.getString("userName"),ReqObj.getString("playerPic"));
                                } catch (JSONException ex) {
                                    Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                                }
                            }
                        });
                    }
                    break;
            }
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    void notifi(String userName,String playerPic){
        NotificationType notification = NotificationType.SUCCESS;
        Image whatsAppImg = new Image(getClass().getResource("../Images/"+playerPic).toString(), true);
        TrayNotification tray = new TrayNotification();
        tray.setTitle("New Player is Online");
        tray.setMessage(userName + " is Online now");
        tray.setRectangleFill(Paint.valueOf("#AA3672"));
        tray.setAnimationType(AnimationType.POPUP);
        tray.setImage(whatsAppImg);
        tray.showAndDismiss(Duration.seconds(2));
    }

    Player convertJsonObjtoPlayer(JSONObject jObj) {
        Player p = new Player();
        try {
            p.setEmail(jObj.getString("email"));
            p.setId(jObj.getInt("id"));
            p.setUser_name(jObj.getString("user_name"));
            p.setFlag(jObj.getBoolean("flag"));
            p.setStatus(jObj.getBoolean("status"));
            p.setScore(jObj.getInt("score"));
            p.setProfile_picture(jObj.getString("profile_picture"));
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return p;
    }

    @Override
    public boolean acceptinvitation(int pOneId, int pTwoId) {

        JSONObject acceptinvitation = new JSONObject();
        try {
            acceptinvitation.put("RequestType", Request.ACCEPT_INVITATION);
            acceptinvitation.put("senderID", pOneId);
            acceptinvitation.put("receiverID", pTwoId);
            output.println(acceptinvitation.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;
    }

    public void playWithComuter(String level) {
        game = new Game(pla, true, level);
        playerIsTurn = true;
        JSONObject isBusy = new JSONObject();
        try {
            isBusy.put("RequestType", Request.IS_BUSY);
            isBusy.put("pID", pla.getId());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        output.println(isBusy);
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                boardObj.setGameDetails(game.getPlayer1().getUser_name(),game.getFp_score(),game.getPlayer1().getProfile_picture(),"Computer", game.getSp_score(),"computer.png", game.getTie_score(),"         ");
            }
        });
        boardObj.hideChatAndSave();
    }

    public void sendPlayedCellRequest(int cellNum, boolean isComputer) {
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
    public void declineInvitation(int pOneId, int pTwoId) {
        JSONObject declineinvitation = new JSONObject();
         try {
            declineinvitation.put("senderID", pOneId);
            declineinvitation.put("receiverID", pTwoId);
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
            sendMsg.put("Message", msg);
            sendMsg.put("senderName", pla.getUser_name());
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
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    boardObj.setGameDetails(game.getPlayer1().getUser_name(),game.getFp_score(),game.getPlayer1().getProfile_picture(),"Computer", game.getSp_score(),"computer.png", game.getTie_score(),"         ");
                }
            });
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
        if (game!=null){
            game = null;
            JSONObject notBusy = new JSONObject();
            try {
                notBusy.put("RequestType", Request.NOT_BUSY);
                notBusy.put("pID", pla.getId());
            } catch (JSONException ex) {
                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            output.println(notBusy);
        }
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

    @Override
    public void leaveGame() {
        JSONObject saveGame = new JSONObject();
        try {
            saveGame.put("RequestType", Request.SAVE_GAME);
            output.println(saveGame.toString());
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
   public Player getPlayer(){
        return pla;
    }    
    
}
