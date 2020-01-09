/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import tictactoe.DbConnection;
import tictactoe.Player;
import utils.Request;
import Server.ServerHandler;
import java.util.HashMap;
import java.util.Map;

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
    Vector<Player> offlinePlayers;
    Vector<Player> onlinePlayers;

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public ServerSideClass(PrintStream ps,DataInputStream dis) {
        this.ps=ps;
        this.dis=dis;
        db= new DbConnection();
        onlinePlayers= new Vector<>();
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
                System.out.println(p.getEmail());
                ps.println(singInBack.toString());
                ServerControl.playerMap.put(p.getId(),s);

            } 
            catch (JSONException ex) {
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            } 
        } 
        else 
        {
            try {
                singInBack.put("RequestType",Request.LOGIN_FAILED);
                System.out.println(p.getEmail());
                ps.println(singInBack.toString());
            } catch (JSONException ex) {
                Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    @Override
    public boolean signUP(String userName, String email, String password) {
        boolean sUpStatus=db.signUp(userName, password, email);
        JSONObject singUpBack= new JSONObject();
        if(sUpStatus == true)
        {
            int size=db.getV().size()-1;
            Player p = db.getV().get(size);
            System.out.println("test "+p.getEmail());
            onlinePlayers.add(p);
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
    public void saveGame(int pOneID, int pTwoID, int pOneScore, int pTwoScore, String[] board) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public char checkWinner() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Vector<Player> getOnlineUser() {
        Player p = new Player();
        for (int i = 0; i < db.getV().size(); i++) {
            if (db.getV().get(i).getFlag() == false)
            {
                offlinePlayers.add(db.getV().get(i));
            } 
            else 
            {
                onlinePlayers.add(db.getV().get(i));
            }
        }
        return onlinePlayers;
    }

    @Override
    public Vector<Player> getOfflineUser() {
       return offlinePlayers;   
    }

    @Override
    public Vector<String> fillLsitofBusyUser() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void leaveGame(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logOut(int pID) {
       
    }

    @Override
    public void setGameBoard(Player pOne, Player pTwo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setTieCounter() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void reciveRequestFromPlayer(int pID) {
        System.out.println(pID);
        try {
            JSONObject sendRequest= new JSONObject();
            sendRequest.put("RequestType",Request.INVITE_PLAYER);
            ps.println(sendRequest.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println(ServerControl.playerMap.get(pID).s);
    }

    @Override
    public void sendRequestToOtherPlayer(ServerHandler s) {
        try {
            JSONObject sendRequest= new JSONObject();
            sendRequest.put("RequestType",Request.INVITE_PLAYER);
            s.Ps.println(sendRequest.toString());
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }

    }


    @Override
    public void acceptPlayerRequest(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}

