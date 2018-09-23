/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.util.Date;

/**
 *
 * @author Mart_
 */
public class User {
    
    private int id;
    private String name;
    private String type;
    private String password;
    private Date date;

    public User(String name, String type) {
        this.name = name;
        this.type = type;
    }
    
    
    
}
