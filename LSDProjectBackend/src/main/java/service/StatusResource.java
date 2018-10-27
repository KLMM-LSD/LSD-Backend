/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

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
@Path("/status")
public class StatusResource {

    private static final String STATUS_ALIVE = "Alive";
    private static final String STATUS_DOWN = "Down";
    private static final String STATUS_UPDATE = "Update";

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of StatusResource
     */
    public StatusResource() {

    }

    @GET
    @Produces(MediaType.TEXT_PLAIN)
    public Response getStatuis() {
        return Response.ok(STATUS_ALIVE).build();
    }
}
