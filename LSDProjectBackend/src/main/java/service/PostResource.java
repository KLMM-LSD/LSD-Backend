/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DBLayer.PostQueries;
import entities.Posts;
import java.sql.SQLException;
import javax.json.JsonObject;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Micha
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
    public String getPost() {
        String b = "Hello there!";
        return b;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Posts postPost(Posts post) throws SQLException {
        PostQueries postQ = new PostQueries();
        return postQ.createPost(post);
    }
}
