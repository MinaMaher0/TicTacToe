/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;


import java.io.DataInputStream;
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

/**
 *
 * @author aAbdelnaby
 */
public class ServerSideClass implements Server {

    private static final String name = "admin";
    private static final String password = "admin";
    DbConnection db;

    Vector<Player> offlinePlayers;
    Vector<Player> onlinePlayers;

    ServerSocket sSocket;
    Socket s;
    DataInputStream dis;
    PrintStream ps;

    public static String getName() {
        return name;
    }

    public static String getPassword() {
        return password;
    }

    public ServerSideClass() {
        db=new DbConnection();
        startServer();
    }

    @Override
    public void startServer() {
        try {
            sSocket = new ServerSocket(5007);
            System.out.println("server started");
            while(true)
            {
                s = sSocket.accept();
                System.out.println("accepted: " + s.toString());
                dis = new DataInputStream(s.getInputStream());
                ps = new PrintStream(s.getOutputStream());
                ps.println("welcome");
                
                String test = dis.readLine();
                JSONObject json = new JSONObject(test);
                //System.out.println(test);
                System.out.println(json.getString("password"));
                System.out.println(json.getString("userName"));
            }
        } catch (IOException e) {
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void stopServer() {
        try {
            
            ps.close();
            dis.close();
            s.close();
            sSocket.close();
        } catch (Exception e) {
        }
    }

    @Override
    public boolean signIN(String userName, String password) {
        db.signIn(userName, password);
        return true;
    }

    @Override
    public boolean signUP(String userName, String email, String password) {
        db.signUp(userName, password, email);
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void sendRequestToOtherPlayer(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void acceptPlayerRequest(int pID) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public static void main(String[] args) {
        new ServerSideClass();
    }

}
