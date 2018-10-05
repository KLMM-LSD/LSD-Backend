/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Posts;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public void createPost(Posts post){
    // Type Comment / Discussion
    
    }
    
    public List<Posts> userPosts(int userid) {
        List<Posts> posts = new ArrayList();
        return posts;
    }
    
}
