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

        Post p1 = new Post();
        Post p2 = new Post();
        Post p3 = new Post();
        Post p4 = new Post();
        Post p5 = new Post();

        User u1 = new User(1, "user", "poobread", "abcdef", "I am a user");
        User u2 = new User(2, "user", "einstein", "dasdas", "I am clever");

        p1.initStory(1, 1, "Story quite a story");
        p2.initComment(2, 1, 2, "Comment good story");
        p3.initComment(3, 2, 1, "comment thanks dude");

        p4.initStory(4, 2, "Story I am drunk");
        p5.initComment(5, 4, 1, "Commetn goos story");

        uq.insertUser(u1);
        uq.insertUser(u2);

        pq.insertStory(p1);
        pq.insertCommentWithLookup(p2);
        pq.insertCommentWithLookup(p3);

        pq.insertStory(p4);
        pq.insertCommentWithLookup(p5);

    }

    public static void testGet() throws SQLException {
        PostQueries pq = new PostQueries();
        ArrayList<Post> ret = pq.getThread(7);

        for (Post p : ret) {
            System.out.println(p.postcontent);
        }
    }

    public static void main(String[] args) throws SQLException {
        initUsers();
    }
}
