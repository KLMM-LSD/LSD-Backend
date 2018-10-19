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

    public static void setUp() throws SQLException, ClassNotFoundException {
        if (access == null) {
            access = new DatabaseAccess();
        }
        if (connection == null) {
            connection = access.getConnection();
        }
    }

    /* COUNT upvotes, count DOWN, evt- sum */
    public int getTotalPostRating(int postid) {
        return 0;
    }
    
    /* try to submuit vote */
    public void submitvote(){
    
    }

}
