/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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

    /* COUNT upvotes, count DOWN, evt- sum */
    public int getTotalPostRating(int postid) throws SQLException {
        PreparedStatement pstmt = connection.prepareStatement("SELECT SUM(ratingvalue) AS sumratings FROM lsd.ratings WHERE ratingpostid = ? LIMIT 1;");
		pstmt.setInt(1, postid);
		ResultSet rs = pstmt.executeQuery();
		if(!rs.next()){
			//Post has no votes, which is normal.
		}
		return rs.getInt(1);
    }
    
    /* try to submuit vote */
    public void submitvote(int userid, int postid, int ratingvalue){
		PreparedStatement pstmt = connection.prepareStatement("INSERT INTO ratings VALUES (?,
    }

}
