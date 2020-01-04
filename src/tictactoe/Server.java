/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ServerPackage;
import java.util.ArrayList;

/**
 *
 * @author DELL
 */
public interface Server{
    
    public void startServer();
    
    //send ack to online players to save game state if they play
    public void stopServer();
    
    public boolean signIN(String userName,String password);
    
    
    public boolean signUP(String userName,String email,String password);
    
    //when user logout set new points to consider which level he belongs to
    public void updateUserScore(String userName,int score);
    
    //save state of game "can replace all of this with object of class GAME"
    public void saveGame(int pOneID,int pTwoID,int pOneScore,int pTwoScore,String[] board);
    
    public Game checkDBForSamePlayersSavedGame(int pOneID,int pTwoID);
    
    //in its body should call function updateScore for the winner 
    public char checkWinner();
    
    //in its body should check state of the online if busy or not 
    public ArrayList<String> getOnlineUser();
     
    public ArrayList<String> getOfflineUser();
    
    public ArrayList<String> fillLsitofBusyUser();
    
    //remove from BusyList and Change state in list as free 
    public void leaveGame(int pID);
    
    //deletc from online and set at offline and set state of player in DB
    public void logOut(int pID);
    
    //set board for users to play
    public void setGameBoard(Palyer pOne,Palyer pTwo);
    
    //inside it will counter++ and call set Game
    public void setTieCounter();

    //from player if wants to play with ither player 
    public void reciveRequestFromPlayer(int pID);
    
    //send request to other player
    public void sendRequestToOtherPlayer(int pID);
    
    //player two accpet request from other player
    public void acceptPlayerRequest(int pID);
        
}
