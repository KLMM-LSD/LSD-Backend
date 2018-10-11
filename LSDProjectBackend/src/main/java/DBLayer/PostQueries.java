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
    public Posts createPost(Posts post) throws SQLException {
        Posts p = new Posts();
        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO posts (posttype, posttimestamp, postcontent) "
                + "VALUES (?,?,?)");
        pstmt.setString(1, p.getPosttype());
        pstmt.setInt(2, p.getPostparentid());
        pstmt.setLong(2, System.currentTimeMillis());
        pstmt.setString(3, p.getPostcontent());
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
