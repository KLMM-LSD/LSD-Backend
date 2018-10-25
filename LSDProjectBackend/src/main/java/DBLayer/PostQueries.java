/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

/**
 *
 * @author Lasse
 */
public class PostQueries {

    public void insertStory(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        String query = "INSERT INTO posts VALUES (?, ?, NULL, ?, ?, NULL, ?)";
        PreparedStatement st = con.prepareStatement(query);

        st.setInt(1, p.postid);
        st.setString(2, p.posttype);
        /* story er root, ingen parent */
        st.setLong(3, p.posttimestamp);
        st.setInt(4, p.postauthorid);
        /* story er root, ingen threadid */
        st.setString(5, p.postcontent);

        st.execute();

        con.close();
    }
}
