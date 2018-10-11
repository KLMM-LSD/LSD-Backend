/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Posts;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.json.JsonObject;

/**
 *
 * @author Mart_
 */
public class PostQueries {

    private static DatabaseAccess access;
    private static Connection connection;

    public void setUp()  {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            try {
                connection = access.getConnection();
            } catch (SQLException ex) {
                Logger.getLogger(PostQueries.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    
    // Måske postThreadId
    public Posts createPost(Posts post) throws SQLException {
        Posts p = new Posts();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO posts (postid, posttype, postparentid, posttimestamp, postauthorid, postcontent)"
                + "VALUES (?,?,?,?,?,?);");
        pstmt.setInt(1, p.getPostid());
        pstmt.setString(2, p.getPosttype());
        pstmt.setInt(3, p.getPostauthorid());
        pstmt.setLong(4, System.currentTimeMillis());
        pstmt.setInt(5, p.getPostauthorid());
        pstmt.setString(6,p.getPostcontent());
        pstmt.execute();
        return post;
    }

    public void updatePost(JsonObject js) {
//        PreparedStatement pstmt
    }

    public List<Posts> userPosts(int userid) {
        List<Posts> posts = new ArrayList();
        return posts;
    }

    public int sumOfPosts() throws SQLException {
        int count = 0;
        Statement stmt = connection.createStatement();
        String query = "SELECT * FROM posts;";
        ResultSet rs = stmt.executeQuery(query);
        while (rs.next()) {
            count++;
        }
        return count;
    }

}
