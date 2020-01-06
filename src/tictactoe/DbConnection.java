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

/**
 *
 * @author hp
 */
public class DbConnection {

    Vector<Player> v = new Vector<>();
    int indexP = 0;
    Connection conn = null;
    Statement st = null;

  public DbConnection() {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/tic_tac_toe", "root", "password");
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
                Player e = new Player(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getBoolean(8));
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
    
    public void signUp( String user_name , String password , String mail)
    {
        try {
            PreparedStatement pst = conn.prepareStatement("INSERT INTO player (user_name,email,password) " +
                    "VALUES (?,?,?);");
            
            pst.setString(1, user_name);
            pst.setString(2, mail);
            pst.setString(3, password);
            int rs = pst.executeUpdate();
            getData();
        } catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    public Player signIn(String mail , String password)
    {   
        Player p = new Player();
        
        PreparedStatement pst;
        try {
            pst = conn.prepareStatement(" select email , password from player" +"where email= "+mail+" && password="+password+"; ");
 
            ResultSet rs = pst.executeQuery();  
            
            if(rs.next())
             {
                 p.setUser_name(rs.getString("user_name"));
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
            pst = conn.prepareStatement(" select * from game" +"where first_player=? && second_player=?; ");
        
            pst.setInt(1, pId1);
            pst.setInt(2, pId2); 
            ResultSet rs = pst.executeQuery();  
            if(rs.next())
                return true;
        }   catch (SQLException ex) {
            Logger.getLogger(DbConnection.class.getName()).log(Level.SEVERE, null, ex);
        }

        return false ;
    }
        
//    public Game getGame(int pId1 , int pId2)
//    {
//        return null;
//        
//    }
//    public void deleteGame(int pId1, int pId2)
//    {
//        
//    }
//    public void saveGame(Game game)
//    {
//        
//    }
    
    
    
    public static void main(String[] args) {

        DbConnection d = new DbConnection();
//        
//       
//        //d.signUp("m", "m", "mmm");
//        
       Player x=d.signIn("kkkk", "kk");
       
       
       

        
    }

}
