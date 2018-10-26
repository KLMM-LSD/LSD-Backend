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
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
    @Path("frontpage")
    @Produces(MediaType.APPLICATION_JSON)
    public String getFrontPageThreads() throws SQLException {
        PostQueries pq = new PostQueries();
        JsonObject ret = new JsonObject();

        ArrayList<Post> list = pq.getMostRecentStories();
        JsonArray arr_postid = new JsonArray(list.size());
        JsonArray arr_postauthorid = new JsonArray(list.size());
        JsonArray arr_postcontent = new JsonArray(list.size());

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

    @GET
    @Path("recent")
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRecentPost() {
        PostQueries pq = new PostQueries();

        try {
            Post ret = pq.getMostRecentPost();
            return Response.ok(ret.postid).build();
        } catch (SQLException ex) {
            Logger.getLogger(PostResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).build();
        }

    }

    @GET
    @Path("thread")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getThreadPosts(@QueryParam("id") int threadid) {
        PostQueries pq = new PostQueries();
        JsonObject ret = new JsonObject();
        ArrayList<Post> list;

        try {
            list = pq.getThread(threadid);
        } catch (SQLException ex) {
            Logger.getLogger(PostResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).build();
        }

        JsonArray arr_postid = new JsonArray(list.size());
        JsonArray arr_postparentid = new JsonArray(list.size());
        JsonArray arr_postauthorid = new JsonArray(list.size());
        JsonArray arr_postcontent = new JsonArray(list.size());

        for (Post p : list) {
            arr_postid.add(p.postid);
            arr_postparentid.add(p.postparentid);
            arr_postauthorid.add(p.postauthorid);
            arr_postcontent.add(p.postcontent);
        }

        ret.addProperty("len", list.size());

        if (!list.isEmpty() && list.get(0).posttype.equals("story")) {
            ret.addProperty("first_is_story", true);
        } else {
            ret.addProperty("first_is_story", false);
        }

        ret.add("arr_postid", arr_postid);
        ret.add("arr_postparentid", arr_postparentid);
        ret.add("arr_postauthorid", arr_postauthorid);
        ret.add("arr_postcontent", arr_postcontent);

        return Response.ok(ret.toString()).build();
    }
}
