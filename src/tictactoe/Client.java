/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.util.Vector;

/**
 *
 * @author Mina Maher
 */
public interface Client {
    public boolean connect();
    
    public boolean signIn(String userName,String password);
    
    public boolean signUp(String userName,String email,String password);
    
    public Vector<Player> getPlayers();
    
    public void printPlayer(Player p);
    
    public void playWithComputer();
    
    public boolean invitePlayer(int id);
    
    public boolean acceptinvitation(int pOneId,int pTwoId);
    
    public void declineInvitation(int pOneId,int pTwoId);
    
    public void logOut(int pId);
    
    public void sort(Vector<Player> p);
}
