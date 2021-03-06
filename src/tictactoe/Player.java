package tictactoe;


import javax.print.DocFlavor;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Aya Morsi
 */
public class Player implements Comparable<Player> {
    int id,score;
    String user_name,email,token,profile_picture;
    boolean flag,status;
    
    public Player(){}
    
    public Player(int id, String user_name,String  email, String token, String profile_picture, int score, boolean flag){
        this.id=id;
        this.user_name= user_name;
        this.email=email;
        this.token=token;
        this.profile_picture=profile_picture;
        this.score=score;
        this.flag=flag;
    }
    
    public void setId(int id) {
        this.id = id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public void setProfile_picture(String profile_picture) {
        this.profile_picture = profile_picture;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public int getId() {
        return id;
    }

    public String getProfile_picture() {
        return profile_picture;
    }

    public int getScore() {
        return score;
    }

    public String getToken() {
        return token;
    }

    public String getUser_name() {
        return user_name;
    }
      
    public boolean getFlag() {
        return flag;
    }
    
    public String getEmail() {
        return email;
    }
    
    public boolean getStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public int compareTo(Player t) {
        if (this.score >= t.getScore())
            return -1;
        else return 1;
    }
}
