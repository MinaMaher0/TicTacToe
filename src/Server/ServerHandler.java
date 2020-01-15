    
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
    Game game;
    public static Vector <ServerHandler> socketVector = new Vector<ServerHandler>();

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
    
    public void setGame(Game g){
        game=g;
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
                    p1 = getPlayer(json.getInt("senderID"));
                    p2 = getPlayer(json.getInt("receiverID"));                            
                    Game g = new Game(p1, p2);
                    ServerControl.playerMap.get(json.getInt("senderID")).setGame(g);
                    ServerControl.playerMap.get(json.getInt("receiverID")).setGame(g);
                    serverObj.sendStartGameRequest(p1.getId(), p2.getId());
                    break;
                case Request.PLAYED_CELL:
                   int cellNum=json.getInt("cellNum");
                   JSONObject sendCell=new JSONObject();
                   sendCell.put("RequestType", Request.PLAYED_CELL);
                   sendCell.put("cellNum",cellNum);
                   sendCell.put("cellChar","X");
                   ServerControl.playerMap.get(game.getPlayer1().getId()).Ps.println(sendCell.toString());
                   ServerControl.playerMap.get(game.getPlayer2().getId()).Ps.println(sendCell.toString());
                    break;
            }
        } catch (Exception ex) {
            
            Logger.getLogger(ServerSideClass.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
