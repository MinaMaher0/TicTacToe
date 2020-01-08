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
import java.util.Scanner;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
//import org.json.simple.JSONObject;
import org.json.JSONObject;
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

    public PlayerFunctions() {
        try {
            s = new Socket("192.168.43.151", 8000);
            input = new DataInputStream(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            
            //signUp("mina", "minasowar@gmail.com", "More34"); 
            //signIn("mina10@gmail.com", "More34");
            invitePlayer(2);
            
        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {

            @Override
            public void run() {
                while (true) {
                    if (input != null) {
                        try {
                            System.out.println("yyyyyyyyyyyyyy");
                            str = input.readLine();
                            System.out.println(str);
                            RequestHandller(str);
                            System.out.println("server response: " + str);
                                        Scanner s = new Scanner(System.in);

                            s.nextInt();
            signIn("mina10@gmail.com", "More34");
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                }

            }
        }).start();
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
            invitePlayerObject.put("userId", id);
            invitePlayerObject.put("RequestType",Request.INVITE_PLAYER);
            output.println(invitePlayerObject.toString());
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
            } catch (JSONException ex) {
                Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
            }
            input.close();
        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void RequestHandller(String str)
    {
         JSONObject ReqObj = null;
         
        try {
            ReqObj = new JSONObject(str);
            if(ReqObj.get("RequestType").equals(Request.SIGN_UP_SUCCESS.toString()))
            {
                System.out.println("sucess");
            }
            
            if (ReqObj.get("RequestType").equals(Request.SIGN_UP_FAILED.toString())){
                System.out.println("unsecss");
            }
            
            if(ReqObj.get("RequestType").equals(Request.LOGIN_SUCCESS.toString()))
            {
                System.out.println("sign in sucess");
            }
            if(ReqObj.get("RequestType").equals(Request.LOGIN_FAILED.toString()))
            {
                System.out.println("sign in unsucess");
            }
            if(ReqObj.get("RequestType").equals(Request.INVITE_PLAYER_SUCESS.toString()))
            {
                System.out.println("invitation accepted");
            }
             if(ReqObj.get("RequestType").equals(Request.INVITE_PLAYER_FAILED.toString()))
            {
                System.out.println("invitation decliend");
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        System.out.println("tttttttttttttttt");
    }

   public static void main(String[] args) {
        new PlayerFunctions();
       // PlayerFunctions p = new PlayerFunctions();
        // p.signIn("aya", "sjdh5dls");
    }
}
