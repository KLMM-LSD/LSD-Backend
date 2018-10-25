/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faketest;

import DBLayer.PostQueries;
import DBLayer.UserQueries;
import entities.Post;
import entities.User;
import java.sql.SQLException;

/**
 *
 * @author Lasse
 */
public class FakeTest {
    
    public static void initUsers() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        
        User u1 = new User(1, "user", System.currentTimeMillis(),
                "poobread", "abcdef", "I am a user");
        User u2 = new User(2, "user", System.currentTimeMillis(),
                "einstein", "dasdas", "I am clever");
        
        Post p1 = new Post(1, "story", System.currentTimeMillis(), 1, "Quite a story");
        Post p2 = new Post(2, "comment", 1, System.currentTimeMillis(), 2, 1, "GOod story");
        
        uq.insertUser(u1);
        uq.insertUser(u2);
        
        pq.insertStory(p1);
        pq.insertPost(p2);
        
    }
    
    public static void main(String[] args) throws SQLException {
        initUsers();
    }
}
