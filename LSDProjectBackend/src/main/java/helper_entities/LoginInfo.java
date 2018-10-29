/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper_entities;

import com.sun.jersey.core.util.Base64;

/**
 *
 * @author Lasse
 */
public class LoginInfo {

    public String username;
    public String password;

    public LoginInfo() {
    }

    public LoginInfo(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public boolean parseAuth(String auth) {
        System.out.println("######################################## " + auth);
        if (auth == null) {
            return false;
        }

        String[] split = auth.split(" ");
        if (split.length != 2) {
            return false;
        }
        
        String actualDecode = split[1].substring(2,split[1].length()-1);
        
        System.out.println("################################ " + actualDecode);
        String decode = Base64.base64Decode(actualDecode);
        System.out.println("###################################### " + decode);
        String[] parse = decode.split(":");
        this.username = parse[0];
        this.password = parse[1];
        

        return true;
    }

}
