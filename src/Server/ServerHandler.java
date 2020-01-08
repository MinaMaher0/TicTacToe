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
import java.net.Socket;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.json.JSONException;
import org.json.JSONObject;
import utils.Request;

/**
 *
 * @author DELL
 */

class ServerHandler extends Thread {
    ServerSideClass serverObj;
    DataInputStream Dis;
    PrintStream Ps;
    Socket s;
    public static Vector<ServerHandler> socketVector = new Vector<ServerHandler>();

    public ServerHandler(Socket socket) {
        try {
            s=socket;
            Dis = new DataInputStream(socket.getInputStream());
            Ps = new PrintStream(socket.getOutputStream());
            serverObj = new ServerSideClass(Ps,Dis);
            socketVector.add(this);
            start();
        } catch (IOException io) {
            io.printStackTrace();
        }

    }


    public void run() {
        while (true) {
            String str;
            try {
                //sendMessageToAll(str);
                str = Dis.readLine();
                System.out.println("str");
                requestHandler(str);
            } catch (Exception ex) {
                System.out.println("tttttttttttttt");
                this.stop();
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }
    public void requestHandler(String request)
    {
        JSONObject json;   
        try {
            json = new JSONObject(request);
            if(json.getString("RequestType").equals(Request.SIGNUP.toString()))
            {
                serverObj.signUP(json.getString("userName"), json.getString("email"), json.getString("password"));
            } 
            if(json.get("RequestType").equals(Request.LOGIN.toString()))
            {
                serverObj.signIN(json.getString("email"), json.getString("password"));
            } 
            if(json.get("RequestType").equals(Request.INVITE_PLAYER.toString()))
            {
                serverObj.sendRequestToOtherPlayer(json.getInt("userId"));
            } 
            
        } catch (JSONException ex) {
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    

}
