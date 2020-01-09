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
import tictactoe.DbConnection;

/**
 *
 * @author DELL
 */
public class ServerControl {

    ServerSocket sSocket;
    Socket s;
    DbConnection db;
    public static Map <Integer,ServerHandler> playerMap;

    
    public ServerControl()
    {
        db=new DbConnection();
        playerMap= new HashMap <Integer, ServerHandler>();

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
}
