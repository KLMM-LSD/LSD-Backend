/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package service;

import DBLayer.UserQueries;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import entities.User;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

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
        jo.addProperty("userid", u.userid);

        return jo.toString();
    }

    @GET
    @Path("count")
    @Produces(MediaType.TEXT_PLAIN)
    public String getUserCount() throws SQLException {
        UserQueries uq = new UserQueries();
        return "Result: " + uq.countUsers();
    }

    @POST
    @Path("create")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public String fakeCreate(String body) {
        String ret = "todo";

        JsonParser jp = new JsonParser();
        JsonObject jo = jp.parse(body).getAsJsonObject();

        ret += jo.get("username").getAsString();

        return ret;
    }

    @POST
    @Path("signup")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.TEXT_PLAIN)
    public Response signup(String body) {
        User tmp = getValidUserInput(body);
        UserQueries uq = new UserQueries();

        if (tmp == null) {
            return Response.status(400).build();
        }

        try {
            uq.insertUser(tmp);
            return Response.status(200).build();
        } catch (SQLException ex) {
            Logger.getLogger(UserResource.class.getName()).log(Level.SEVERE, null, ex);
            return Response.status(500).build();
        }
    }

    private User getValidUserInput(String body) {
        JsonParser jp = new JsonParser();
        JsonElement je = jp.parse(body);

        if (je == null) {
            return null;
        }

        JsonObject jo = je.getAsJsonObject();
        JsonElement je_username = jo.get("username");

        if (je_username == null) {
            return null;
        }
        JsonElement je_password = jo.get("userpassword");
        if (je_password == null) {
            return null;
        }

        return new User(-1, "user",
                je_username.getAsString(),
                je_password.getAsString());
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
