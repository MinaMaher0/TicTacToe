/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import java.io.DataInputStream;
import java.io.PrintStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import tictactoe.DbConnection;
import tictactoe.Player;
import utils.Request;
import javafx.application.Platform;
import tictactoe.PlayerFunctions;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import tictactoe.Game;
import tictactoe.LeaveGameDialogController;
import static tictactoe.PlayerFunctions.users;

/**
 *
 * @author aAbdelnaby
 */
public class ServerSideClass implements Server {

    private static final String name = "admin";
    private static final String password = "admin";
    DbConnection db;
    DataInputStream dis;
    PrintStream ps;
 
    
    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }
    
    public ServerSideClass() {    
    }
    
  
    
    public ServerSideClass(PrintStream ps,DataInputStream dis) {
        this.ps=ps;
        this.dis=dis;
        db= new DbConnection();
    }
    

    
    @Override
    public boolean signIN(String email, String password,ServerHandler s) {
 
        Player p=db.signIn(email, password);
        JSONObject singInBack= new JSONObject();
       
        if(p != null)
        {
            try {
                singInBack.put("id",p.getId());
                singInBack.put("userName",p.getUser_name());
                singInBack.put("email",p.getEmail());
                singInBack.put("score",p.getScore());
                singInBack.put("pPic",p.getProfile_picture());
                singInBack.put("RequestType",Request.LOGIN_SUCCESS);
                                
                for (int i=0;i<ServerControl.players.size();++i)
                {
                    if (ServerControl.players.get(i).getId()== p.getId())
                    {
                        ServerControl.players.get(i).setFlag(true);
                        ServerControl.players.get(i).setStatus(false);
                        System.out.println("xXx "+ ServerControl.players.get(i).getStatus());
                    }
                }
                
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Platform.runLater(() -> {
                            ServerControl.sHome.setOfflineList();
                        });
                    }
                });
               
                ps.println(singInBack.toString());
                sendNotification(p.getUser_name(),p.getId());
                sendUsers();
                ServerControl.playerMap.put(p.getId(),s);
                System.out.println("PPPPPPPPPPPP");
            } 
            catch (Exception ex) {
                System.out.println("flola : "+ex.toString());
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 
        else 
        {
            try {
                singInBack.put("RequestType",Request.LOGIN_FAILED);
                ps.println(singInBack.toString());
            } catch (JSONException ex) {
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }
    
    public void sendUsers(){
        JSONObject users= new JSONObject();
        try {
            users.put("RequestType",Request.USERS);
            users.put("users",ServerControl.players);
            for (ServerHandler sv : ServerHandler.socketVector)
            {
                sv.Ps.println(users.toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void sendNotification(String userName,int pID){
        JSONObject users= new JSONObject();
        try {
            users.put("RequestType",Request.NOTIFICATION);
            users.put("userName",userName);
            users.put("pID",pID);
            for (ServerHandler sv : ServerHandler.socketVector)
            {
                sv.Ps.println(users.toString());
            }
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean signUP(String userName, String email, String password){
        
        boolean sUpStatus=db.signUp(userName, password, email);
        JSONObject singUpBack= new JSONObject();
        if(sUpStatus == true)
        {
            int size=db.getV().size()-1;
            Player p = db.getV().get(size);
            p.setFlag(true);
            ServerControl.players.add(p);
            
            try {
                singUpBack.put("id",p.getId());
                singUpBack.put("userName",p.getUser_name());
                singUpBack.put("email",p.getEmail());
                singUpBack.put("score",p.getScore());
                singUpBack.put("pPic",p.getProfile_picture());
                singUpBack.put("RequestType",Request.SIGN_UP_SUCCESS);
                ps.println(singUpBack.toString());
                
            } catch (JSONException ex) {
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            }   
        }
        else
        {
            try {
                singUpBack.put("RequestType",Request.SIGN_UP_FAILED);
                ps.println(singUpBack.toString());
            } catch (JSONException ex) {
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public void updateUserScore(String userName, int score) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveGame(Game g) {
        System.out.println("Server Save Game");
        db.saveGame(g);
    }

    @Override
    public char checkWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
    @Override
    public void fillLsitofBusyUser(int p1,int p2) {
        for (Player p:ServerControl.players)
        {
            if (p.getId()== p1 ||p.getId() == p2)
            { 
                p.setStatus(true);
                System.out.println("status is true y prince");
            }
        }
        sendUsers();
    }
        
    @Override
    public void serverFallen() {
       System.out.println("falllllen ");
       JSONObject serverFail= new JSONObject();
        try {
            serverFail.put("RequestType", Request.SERVER_FAILED);
            for(Player p:ServerControl.players)
            {
                if(p.getFlag()==true)
                {   
                    p.setFlag(false);
                    ServerControl.playerMap.get(p.getId()).Ps.println(serverFail.toString());
                    System.out.println("afllennnnnnnnnnnnnnnnnnnnnnnnnnnnnn");
                }
            }
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void logOut(int pID) {
        for (int i=0;i<ServerControl.players.size();++i)
        {
            if (ServerControl.players.get(i).getId()==pID)
            {
                ServerControl.players.get(i).setFlag(false);
            }
        }
    }

    @Override
    public void setGameBoard(Player pOne, Player pTwo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTieCounter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

   
//    public void sendMessagetoPlayer(int senderID,int receiverID,String senderUserName){
//        JSONObject sendRequest=new JSONObject();
//        sendRequest.put("RequestType",Request.SEND_MESSAGE);
//        sendRequest.put("senderID",senderID);
//        sendRequest.put("senderUserName",senderUserName);
//         ServerHandler s =ServerControl.playerMap.get(receiverID);
//            for (Player p : ServerControl.players){
//                if (p.getId()==receiverID){
//                    sendRequest.put("usrName",senderUserName);
//                }
//            }
//            s.Ps.println(sendRequest.toString());
//        
//    }
  
    @Override
    public void sendRequestToOtherPlayer(int senderID,int receiverID,String senderUserName) {
        try {
            
            JSONObject sendRequest= new JSONObject();
            sendRequest.put("RequestType",Request.INVITE_PLAYER);
            sendRequest.put("senderID",senderID);
            sendRequest.put("senderUserName",senderUserName);
            ServerHandler s =ServerControl.playerMap.get(receiverID);
            
            for (Player p : ServerControl.players)
            {
                if (p.getId()==receiverID)
                    sendRequest.put("usrName",senderUserName);
            }
            s.Ps.println(sendRequest.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

     public void recieveMessageFromPlayer(String message,int senderID ,int recieverID){
       
        try {
            System.out.println(message);
            JSONObject sendMessage=new JSONObject();
            sendMessage.put("RequestType",Request.SEND_MESSAGE);
            sendMessage.put("senderID",senderID);
            sendMessage.put("recieverID",recieverID);
            sendMessage.put("Message",message);
              ServerControl.playerMap.get(senderID).Ps.println(sendMessage.toString());
             ServerControl.playerMap.get(recieverID).Ps.println(sendMessage.toString());
           // ps.println(sendMessage.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    
    

    @Override
    public void acceptPlayerRequest(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    public void sendStartGameRequest(int p1 , int p2,Game game)
    {
        JSONObject jsonStart = new JSONObject();
        try {
            jsonStart.put("senderID", p1);
            jsonStart.put("receiverID", p2);
            jsonStart.put("RequestType", Request.START_GAME);
            jsonStart.put("playerOneName", game.getPlayer1().getUser_name());
            jsonStart.put("playerTwoName", game.getPlayer2().getUser_name());
            jsonStart.put("playerOneScore", game.getFp_score());
            jsonStart.put("playerTwoScore", game.getSp_score());
            jsonStart.put("tieScore", game.getTie_score());
            ServerControl.playerMap.get(p1).Ps.println(jsonStart.toString());
            ServerControl.playerMap.get(p2).Ps.println(jsonStart.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void sendRefuseGameRequest(int p1 ,int p2)
    {
        JSONObject jsonRefuse = new JSONObject();
        try {
            for (Player p:ServerControl.players)
            {
                if(p.getId()== p2)
                    jsonRefuse.put("userName", p.getUser_name());
            }
            jsonRefuse.put("RequestType", Request.REFUSE_INVITATION);
            ServerControl.playerMap.get(p1).Ps.println(jsonRefuse.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void sendTurn(int pID){
        try {
            JSONObject plyerTurn=new JSONObject();
            plyerTurn.put("RequestType",Request.PLAYER_TURN);
            ServerControl.playerMap.get(pID).Ps.println(plyerTurn.toString());
            
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
   /* public void sendLeaveGame(int p1, int p2)
    {
          JSONObject jsonLeaveGame = new JSONObject();
        try {
            for (Player p:ServerControl.players)
            {
                if(p.getId()== p1)
                    jsonLeaveGame.put("userName", p.getUser_name());
            }
            jsonLeaveGame.put("RequestType", Request.LEAVE_GAME);
            ServerControl.playerMap.get(p2).Ps.println(jsonLeaveGame.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }*/
    
    public void enableInviteButton(int p1, int p2)
    {
        for(Player p:ServerControl.players)
        {
            if (p.getId() == p1 || p.getId() == p2)
                p.setStatus(false);
        }
        sendUsers();
    }

}

