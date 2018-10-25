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

    private static final String INSERT_STORY_QUERY = "INSERT INTO posts VALUES (?, ?, NULL, ?, ?, NULL, ?)";
    private static final String INSERT_COMMENT_QUERY = "INSERT INTO posts VALUES (?, ?, ?, ?, ?, ?, ?)";

    /* 0 er ikke NULL i MySQL */
    public void insertStory(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();

        PreparedStatement st = con.prepareStatement(INSERT_STORY_QUERY);

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

    public void insertPost(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement st = con.prepareStatement(INSERT_COMMENT_QUERY);

        st.setInt(1, p.postid);
        st.setString(2, p.posttype);
        st.setInt(3, p.postparentid);
        st.setLong(4, p.posttimestamp);
        st.setInt(5, p.postauthorid);
        st.setInt(6, p.postthreadid);
        st.setString(7, p.postcontent);

        st.execute();
        con.close();
    }
}
