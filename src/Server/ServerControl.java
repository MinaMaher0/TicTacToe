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
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.DbConnection;
import tictactoe.Player;

/**
 *
 * @author DELL
 */
public class ServerControl {

    ServerSocket sSocket;
    Socket s;
    DbConnection db;
    ServerSideClass sSC;
    public static Map <Integer,ServerHandler> playerMap;
    public static Vector<Player> players;
    
    public ServerControl()
    {
        db=new DbConnection();
        playerMap = new HashMap <Integer, ServerHandler>();
        players = new Vector<>();
        players=db.getData();
        sSC= new ServerSideClass();
        startServer();
    }
    
    
    public void startServer() {
        try {
            sSocket = new ServerSocket(8000);
            System.out.println("server started");
            while(true)
            {
                s = sSocket.accept();
                new ServerHandler(s);
            }
        } catch (IOException e) {
        }
    }
    
    public void stopServer() {
        try 
        {
            for(ServerHandler s: ServerHandler.socketVector)
            {
                s.s.close();
            }
            sSocket.close();
        } catch (Exception e) {
        }
    }
    public static void main(String[] args) {
        new ServerControl();
    }
}
