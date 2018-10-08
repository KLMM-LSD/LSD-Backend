/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Posts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.json.JsonObject;

/**
 *
 * @author Mart_
 */
public class PostQueries {

    private static DatabaseAccess access;
    private static Connection connection;

    public static void setUp() throws SQLException {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            connection = access.getConnection();
        }
    }

    // Måske postThreadID
    public void createPost(JsonObject js) throws SQLException{
    
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO posts (posttype, parentid, posttimestamp, postauthorid, postcontent) "
                + "VALUES (?,?,?,?,?)"); 
        pstmt.setString(1, js.getString("post_type"));
        pstmt.setInt(2, js.getInt("post_parent"));
        pstmt.setLong(3, System.currentTimeMillis());
        pstmt.setString(4, js.getString("username"));
        pstmt.setString(5, js.getString("post_text"));        
        pstmt.execute();
    }
    
    public void updatePost(JsonObject js) {
//        PreparedStatement pstmt
    }
    
    public List<Posts> userPosts(int userid) {
        List<Posts> posts = new ArrayList();
        return posts;
    }
    
}
