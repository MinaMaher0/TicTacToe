/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Server;

import ServerGui.ServerHomeController;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import tictactoe.DbConnection;
import tictactoe.Player;

/**
 *
 * @author DELL
 */
public class ServerControl extends Thread{

    ServerSocket sSocket;
    Socket s;
    public DbConnection db;
    ServerSideClass sSC;
    public static Map <Integer,ServerHandler> playerMap;
    Thread startServer;
    public static Vector <Player> players;
    public static ServerHomeController sHome;

    public ServerControl()
    {
        db=new DbConnection();
        playerMap = new HashMap <Integer, ServerHandler>();
        players = new Vector<>();
        players=db.getData();
        sSC= new ServerSideClass();
    }
    
    public void setHomeControllerObj(ServerHomeController sH)
    {
        sHome=sH;
    }
    public void startServer() 
    {
        startServer=new Thread(() -> {
        try {
                sSocket = new ServerSocket(8000);
                System.out.println("server started");
                    while(true)
                    {
                        s = sSocket.accept();
                        new ServerHandler(s,db);
                    }
                }
        catch (IOException e) {}
        });
        startServer.start();
    }
           
    
    
    public void stopServer() {
        System.out.println("stoooooooooop");
        sSC.serverFallen();
        try 
        {
           for(ServerHandler s: ServerHandler.socketVector)
            {
                s.Ps.close();
                s.Dis.close();
                s.s.close();
            }
            db.closeConnnection();
            startServer.stop();
            sSocket.close();
        } catch (Exception e) {
        }
    }
}
