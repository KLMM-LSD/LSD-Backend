/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DBLayer.PostQueries;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import entities.Post;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Lasse
 */
@Path("/posts")
public class PostResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public PostResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getFrontPageThreads() throws SQLException {
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

        return ret.toString();
    }

}
