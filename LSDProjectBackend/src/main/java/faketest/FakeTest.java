/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package faketest;

import DBLayer.PostQueries;
import DBLayer.UserQueries;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
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

        User u1 = new User(-1, "user", "poobread", "abcdef");
        User u2 = new User(-1, "user", "einstein", "dasdas");

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
        ArrayList<Post> ret = pq.getThread(1);
        JsonObject list = new JsonObject();

        JsonArray arr_postids = new JsonArray();
        JsonArray arr_postparentid = new JsonArray();
        JsonArray arr_postauthorid = new JsonArray();
        JsonArray arr_postthreadid = new JsonArray();
        JsonArray arr_postcontent = new JsonArray();

        for (Post p : ret) {
            arr_postids.add(p.postid);
            arr_postparentid.add(p.postparentid);
            arr_postauthorid.add(p.postauthorid);
            arr_postthreadid.add(p.postthreadid);
            arr_postcontent.add(p.postcontent);
        }
        
        
        list.addProperty("length", ret.size());

        list.add("arr_postids", arr_postids);
        list.add("arr_postparentid", arr_postparentid);
        list.add("arr_postauthorid", arr_postauthorid);
        list.add("arr_postthreadid", arr_postthreadid);
        list.add("arr_postcontent", arr_postcontent);

        System.out.println(list.toString());
    }

    public static void main(String[] args) throws SQLException {
        testGet();
    }
}
