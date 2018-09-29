/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import java.sql.Connection;
import java.sql.SQLException;

/**
 *
 * @author Mart_
 */
public class RatingQueries {

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

    public int getTotalCommentRating(int userid) {
        return 0;
    }

    public int getTotalDiscussionRating(int userid) {
        return 0;
    }
    
    //Giv Points +/-
    public void vote(){
    
    }

}
