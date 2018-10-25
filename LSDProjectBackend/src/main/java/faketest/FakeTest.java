/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faketest;

import DBLayer.UserQueries;
import entities.User;
import java.sql.SQLException;

/**
 *
 * @author Lasse
 */
public class FakeTest {

    public static void initUsers() throws SQLException {
        UserQueries uq = new UserQueries();
        User u = new User(1, "user", System.currentTimeMillis(),
                "poobread", "abcdef", "I am a user");
       
        uq.insertUser(u);
    }

    public static void main(String[] args) throws SQLException {
        initUsers();
    }
}
