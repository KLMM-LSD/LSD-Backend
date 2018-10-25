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
import java.util.ArrayList;

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
        
        Post p1 = new Post(1, "story", System.currentTimeMillis(), 1, "Story Quite a story");
        Post p2 = new Post(2, "comment", 1, System.currentTimeMillis(), 2, 1, "Comment GOod story");
        Post p3 = new Post(3, "comment", 2, System.currentTimeMillis(), 1, 1, "Comment Thanks dude");
        
        Post p4 = new Post(4, "story", System.currentTimeMillis(), 2, "Story I am drunk");
        Post p5 = new Post(5, "comment", 4, System.currentTimeMillis(), 1, 4, "Comment GOod story");
        
        uq.insertUser(u1);
        uq.insertUser(u2);
        
        pq.insertStory(p1);
        pq.insertPost(p2);
        pq.insertPost(p3);
        
        pq.insertStory(p4);
        pq.insertPost(p5);
        
    }
    
    public static void testGet() throws SQLException {
        PostQueries pq = new PostQueries();
        ArrayList<Post> ret = pq.getThread(1);
        
        for (Post p : ret)
            System.out.println(p.postcontent);
    }
    
    public static void main(String[] args) throws SQLException {
        testGet();
    }
}
