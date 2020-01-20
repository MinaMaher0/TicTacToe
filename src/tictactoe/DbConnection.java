/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tictactoe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Vector;
import java.util.logging.Level;
import java.util.logging.Logger;
import tictactoe.Player;

/**
 *
 * @author hp
 */
public class DbConnection {

    Vector <Player> v = new Vector<>();
    int indexP = 0;
    Connection conn = null;
    Statement st = null;

  public DbConnection() {

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");


            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic_tac_toe","root","ahmedxd22");

//jdbc:mysql://localhost:3306/tic_tac_toe
            st = conn.createStatement();
        } catch (SQLException ex) {
            System.out.println("test");
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public Vector<Player> getData() {
        v.clear();
        String queryString = "select * from player";
        ResultSet rs = null;
        try {
            rs = st.executeQuery(queryString);
            
            while (rs.next()) {
                Player e = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8));
                v.add(e);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return v ;
    }
    /*public int getNewId()
    {
        return v.get(v.size()-1).getId()+1;
    }*/

    public Vector<Player> getV() {
        return v;
    }
    
    public boolean signUp( String user_name , String password , String mail, String image)
    {
        try {
            
            PreparedStatement pst = conn.prepareStatement("INSERT INTO player (user_name,email,password,Profile_picture) " +
                    "VALUES (?,?,?,?);");
            
            pst.setString(1, user_name);
            pst.setString(2, mail);
            pst.setString(3, password);
            pst.setString(4, image);
            int rs = pst.executeUpdate();
            getData();
            return rs!=0;
           
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    public Player signIn(String mail , String password)
    {   
        Player p = new Player();
        PreparedStatement pst;
        try{
            pst =conn.prepareStatement("SELECT * FROM player WHERE email=? and password=?;");
            pst.setString(1,mail);
            pst.setString(2,password);
            ResultSet rs = pst.executeQuery();  
            if(rs.next())
            {
                p.setId(rs.getInt("id"));
                p.setUser_name(rs.getString("user_name"));
                p.setEmail(rs.getString("email"));    
                p.setProfile_picture(rs.getString("profile_picture"));
                p.setScore(rs.getInt("score"));
                p.setFlag(rs.getBoolean("flag"));
                return p ;
            }
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    public boolean checkAvailableGame(int pId1 , int pId2)
    {
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement("SELECT * FROM game WHERE (first_player=? AND second_player=?) OR (first_player= ? AND second_player=?);");
            
            pst.setInt(1, pId1);
            pst.setInt(2, pId2);
            pst.setInt(4, pId1);
            pst.setInt(3, pId2); 
            ResultSet rs = pst.executeQuery();  
            if(rs.next())
                return true;
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false ;
    }
     public Player getPlayer(int pId) {
        PreparedStatement pst;
        
        try {
            pst = conn.prepareStatement("  select * from player " +"where id= ?; ");
            pst.setInt(1, pId);
            ResultSet rs = pst.executeQuery();  
            if (rs.next()) {
                Player p = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8));
                return p ;
            }
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null ;
    }
        
    public Game getGame(int pId1 , int pId2)
    {       
        
        PreparedStatement pst;
        try {
            
             pst = conn.prepareStatement(" select * from game " +"where (first_player= ? AND second_player=?) OR (first_player= ? AND second_player=?);");
             pst.setInt(1, pId1);
             pst.setInt(2, pId2);
             
             pst.setInt(4, pId1);
             pst.setInt(3, pId2);
             
             ResultSet rs = pst.executeQuery();  
            
            if(rs.next())
             {
                 System.out.println("at DB=  "+rs.getString(7).toCharArray()[0]+" String = "+rs.getString(7));
                 Game g = new Game(getPlayer(rs.getInt(2)), getPlayer(rs.getInt(3)),rs.getInt(4), rs.getInt(5),rs.getString(6).toCharArray(), rs.getInt(7), rs.getInt(8), rs.getInt(9));  
                 return g ;
             }
            
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
        return null;
    }
    public void deleteGame(int pId1, int pId2)
    {
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(" DELETE FROM game " +"where (first_player= ? and second_player=?) OR (first_player= ? and second_player=?); ");
            pst.setInt(1, pId1);
            pst.setInt(2, pId2);
            
            pst.setInt(4, pId1);
            pst.setInt(3, pId2);
            
            pst.executeUpdate();  
            
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
   public void saveGame(Game game) 
    {
        System.out.println("DB save Game ///////");
       int p1 = game.getPlayer1().getId();
       int p2 = game.getPlayer2().getId();
       
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO game (first_player,second_player,playerTurn,first_cell,game_board,tie,fp_score,sp_score) " +
                    "VALUES (?,?,?,?,?,?,?,?);");
            
            pst.setInt(1, p1);
            pst.setInt(2, p2);
            pst.setInt(3, game.getPlayerTurn());
            pst.setInt(4, game.getPlayerPlayedFirstCell());
            pst.setString(5, String.valueOf(game.getBoard()));
            pst.setInt(6, game.getTie_score());
            pst.setInt(7, game.getFp_score());
            pst.setInt(8, game.getSp_score());
            int rs = pst.executeUpdate();
            System.out.println("Game Saved Successfully");
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
       
    }
    public Boolean updateScore(int pId)
    {
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(" UPDATE player set score= score+?" +"where id=?; ");
             
            pst.setInt(1,10);
            pst.setInt(2, pId); 
            int rs = pst.executeUpdate();  
            if(rs!=0)
                return true;
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false ;
    }
    public void closeConnnection()
    {
        try {
            st.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void main(String[] args) {
        DbConnection d = new DbConnection();
      
       d.getData();
        
    }

}
