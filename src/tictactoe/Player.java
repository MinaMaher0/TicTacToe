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
public class Player {
    int id,score;
    String user_name,email,password, token,profile_picture;
    boolean flag;
    
    public Player(){    }
    
    public Player(int id, String user_name, String  email, String password, String token, String profile_picture, int score, boolean flag  ){
        this.id=id;
        this.user_name= user_name;
        this.email=email;
        this.password= password;
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

    public void setPassword(String password) {
        this.password = password;
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

    public String getPassword() {
        return password;
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
    
    
}
