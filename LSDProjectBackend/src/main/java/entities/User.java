/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lasse
 */
public class User {

    public int userid;
    public String usertype;
    public String username;
    public String userpassword;
    public String userabout;

    public User(int userid, String usertype, String username, String userpassword, String userabout) {
        this.userid = userid;
        this.usertype = usertype;
        this.username = username;
        this.userpassword = userpassword;
        this.userabout = userabout;
    }

    

}
