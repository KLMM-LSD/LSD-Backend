/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DBLayer.PostQueries;
import entities.Post;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

/**
 * REST Web Service
 *
 * @author Lasse
 */
@Path("/latest")
public class LatestResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of LatestResource
     */
    public LatestResource() {

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getRecentPostId() {
        PostQueries pq = new PostQueries();

        try {
            Post ret = pq.getMostRecentPost();
            return Response.ok(ret.postid).build();

        } catch (SQLException ex) {
            Logger.getLogger(PostResource.class
                    .getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).build();
        }

    }
}
