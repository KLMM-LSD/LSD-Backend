/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DBLayer.UserQueries;
import com.google.gson.JsonObject;
import entities.User;
import java.sql.SQLException;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Lasse
 */
@Path("/users")
public class UserResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of PostResource
     */
    public UserResource() {
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public String getUser() {
        String b = "Hello there!";
        return b;
    }

    @GET
    @Path("poobread")
    @Produces(MediaType.APPLICATION_JSON)
    public String getPoobread() throws SQLException {
        UserQueries uq = new UserQueries();
        User u = uq.getUserByName("poobread");
        JsonObject jo = new JsonObject();

        jo.addProperty("username", u.username);
        jo.addProperty("userabout", u.userabout);

        return jo.toString();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserCount() throws SQLException {
        UserQueries uq = new UserQueries();
        return "Result: " + uq.countUsers();
    }
//
//    @POST
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public void postUser(JsonObject js) throws SQLException {
//        UserQueries postQ = new UserQueries();
//        Users user = new Users();
//        user.setUserid(js.getInt("userid"));
//        user.setUsername(js.getString("username"));
//        user.setUsertype(js.getString("usertype"));
//        user.setUsertimestamp(System.currentTimeMillis());
//        user.setUserpassword(js.getString("userpassword"));
//        user.setUserabout(js.getString("userabout"));
//        postQ.createUser(user);
//    }
}
