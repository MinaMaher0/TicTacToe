    
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
import tictactoe.Game;
import tictactoe.Player;
import utils.Request;

/**
 *
 * @author DELL
 */

class ServerHandler extends Thread {
    
    ServerSideClass serverObj;
    DataInputStream Dis;
    public PrintStream Ps;
    Socket s;
    ServerHandler me;
    Player p1,p2;
    public static Vector <ServerHandler> socketVector = new Vector<ServerHandler>();
    Game g;
    
    public void setGame(Game pg){
        pg=g;
    }
            
            
    public ServerHandler(Socket socket) {
        try {
            s=socket;
            Dis = new DataInputStream(socket.getInputStream());
            Ps = new PrintStream(socket.getOutputStream());
            serverObj = new ServerSideClass(Ps,Dis);
            socketVector.add(this);
            me=this;
            start();
        } catch (IOException io) {
            io.printStackTrace();
        }
    }

    
    public void run() {
        while (true) {
            String str;
            try {
                str = Dis.readLine();
                System.out.println(str);
                requestHandler(str);
            } catch (Exception ex) {
                System.out.println(ex.toString());
                System.out.println("tttttttttttttteee");
                this.stop();
                Logger.getLogger(ServerHandler.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public Player getPlayer (int id)
    {
        for (int i =0 ; i<ServerControl.players.size();i++)
        {
            if (ServerControl.players.get(i).getId()== id)
                return ServerControl.players.get(i);
        }
     return null;
            
    }
    
    
    public void requestHandler(String request)
    {
        JSONObject json;   
        try {
            json = new JSONObject(request);
            switch (json.getInt("RequestType"))
            {
                case Request.SIGNUP:
                {
                     serverObj.signUP(json.getString("userName"), json.getString("email"), json.getString("password"));
                     break;
                }
                case Request.LOGIN:
                {
                    serverObj.signIN(json.getString("email"), json.getString("password"),this);
                    break;
                }
                case Request.INVITE_PLAYER:
                {
                    serverObj.sendRequestToOtherPlayer(json.getInt("senderID"),json.getInt("receiverID"),json.getString("senderUserName"));
                    break;
                }
                
                case Request.ACCEPT_INVITATION:
                {
                   System.out.println(json);
                   p1 = getPlayer(json.getInt("player1Id"));
                   p2 = getPlayer(json.getInt("player2Id"));
                   Game game = new Game(p1, p2);
                   g=game;
                   ServerControl.playerMap.get(p1.getId()).g=game;
                   
                   serverObj.sendStartGameRequest(p1.getId(), p2.getId());

                   
                    break;
                }
                case Request.PLAYED_CELL:
                {
                    
                    ServerControl.playerMap.get(p2.getId()).g.chooseCell(json.getInt("cellNumber"));
                    ServerControl.playerMap.get(p1.getId()).g.chooseCell(json.getInt("cellNumber"));
                    

                    break;
                }
            
            }
        } catch (Exception ex) {
            
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
    
