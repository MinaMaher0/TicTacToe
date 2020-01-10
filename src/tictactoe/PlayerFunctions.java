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
import org.json.JSONArray;
import org.json.JSONException;
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
    ControlButtonsController cbController = null;
    
    public void setControlButtonsController(ControlButtonsController obj){
        cbController=obj;
        cbController.showPlayers();
    }
    
    public static Vector<Player> users;
    public PlayerFunctions() {
        try {

            s = new Socket("127.0.0.1", 8000);
            input = new DataInputStream(s.getInputStream());
            output = new PrintStream(s.getOutputStream());
            
            users= new Vector<>();

        } catch (IOException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    if (input != null) {
                        try {
                            str = input.readLine();
                            System.out.println(str);
                            RequestHandller(str);
                            System.out.println("server response: " + str);
                    //  Scanner s = new Scanner(System.in);
                    // s.nextInt();
                     //    signIn("m@m.com", "1234");
                        } catch (IOException ex) {
                            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
                        }
                    }
                else{
                        System.out.println("connection lost");
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
            switch(ReqObj.get("RequestType").hashCode()){
                case Request.SIGN_UP_SUCCESS:
                     System.out.println("sucess");
                     break;
                case Request.SIGN_UP_FAILED :
                    System.out.println("unsecss");
                case Request.LOGIN_SUCCESS:
                    System.out.println("sign in sucess");
                    break;
                case Request.LOGIN_FAILED:
                    System.out.println("sign in unsucess");
                    break;
                case Request.INVITE_PLAYER_SUCESS:
                    System.out.println("invitation accepted");
                    break;
                case Request.INVITE_PLAYER_FAILED:
                    System.out.println("invitation decliend");
                    break;
                case Request.INVITE_PLAYER:
                    System.out.println(ReqObj.toString());
                    break;
                case Request.USERS:
                    users.clear(); // remove it when run clients from different laptops
                    JSONArray jArr = ReqObj.getJSONArray("users");
                    for (int i =0 ;i<jArr.length();++i){
                        Player p = convertJsonObjtoPlayer(jArr.getJSONObject(i));
                        users.add(p);
                    }
                    if (cbController!=null)
                    cbController.showPlayers();
                    break;
            }
            
        } catch (JSONException ex) {
            Logger.getLogger(PlayerFunctions.class.getName()).log(Level.SEVERE, null, ex);
        }
        

    }

    Player convertJsonObjtoPlayer(JSONObject jObj){
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
}
