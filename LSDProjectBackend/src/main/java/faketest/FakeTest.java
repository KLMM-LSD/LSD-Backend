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
    
    public static void testGet() throws SQLException {
        PostQueries pq = new PostQueries();
        JsonObject ret = new JsonObject();

        JsonArray arr_postid = new JsonArray();
        JsonArray arr_postauthorid = new JsonArray();
        JsonArray arr_postcontent = new JsonArray();

        ArrayList<Post> list = pq.getMostRecentStories();

        for (Post tmp : list) {
            arr_postid.add(tmp.postid);
            arr_postauthorid.add(tmp.postauthorid);
            arr_postcontent.add(tmp.postcontent);
        }

        ret.add("arr_postid", arr_postid);
        ret.add("arr_postauthorid", arr_postauthorid);
        ret.add("arr_postcontent", arr_postcontent);

        ret.addProperty("len", list.size());
        
        System.out.println(ret.toString());
    }

    public static void main(String[] args) throws SQLException {
        testGet();
    }
}
