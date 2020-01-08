/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.simple.JSONObject;

/**
 *
 * @author Aya Morsi
 */
public class PlayerFunctions implements Client {

    String str;
    DataInputStream input;
    PrintStream output;
    Socket s;

    public PlayerFunctions() {
       /* try {
            s = new Socket("7.7.7.42", 8000);
            input = new DataInputStream(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            signIn("hello", "aya");
            signUp("aya", "hs5sk", "aya@gmail.com");
        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (input != null) {
                        try {
                            str = input.readUTF();
                            System.out.println("server response: " + str);
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        }).start();*/
    }

    @Override
    public boolean connect() {
        try {
            s = new Socket("7.7.7.42", 8000);
            input = new DataInputStream(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            signIn("hello", "aya");
            signUp("aya", "hs5sk", "aya@gmail.com");
        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (input != null) {
                        try {
                            str = input.readUTF();
                            System.out.println("server response: " + str);
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        }).start();
        return true;
    }

    @Override
    public boolean signIn(String userName, String password) {
        JSONObject SigninObject = new JSONObject();
        SigninObject.put("userName", userName);
        SigninObject.put("password", password);
        //System.err.println(SigninObject.toString());
        output.println(SigninObject.toString());
        return true;
    }

    @Override
    public boolean signUp(String userName, String email, String password) {
       JSONObject SignupObject = new JSONObject();
       SignupObject.put("userName", userName);
       SignupObject.put("email", email);
       SignupObject.put("password", password);
      // System.err.println(SignupObject.toString());
       output.println(SignupObject.toString());
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void logOut(int pId) {
        JSONObject logOutObject = new JSONObject();
        logOutObject.put("userId", pId);
        output.close();
    }

   /* public static void main(String[] args) {
        new PlayerFunctions();
    }*/
}
