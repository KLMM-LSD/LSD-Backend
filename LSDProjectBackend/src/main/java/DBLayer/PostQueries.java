/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Post;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Lasse
 */
public class PostQueries {

    private static final String INSERT_STORY_QUERY = "INSERT INTO posts VALUES (?, ?, NULL, ?, ?, NULL, ?)";
    private static final String INSERT_COMMENT_QUERY = "INSERT INTO posts VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GET_THREAD_QUERY = "SELECT * FROM posts WHERE posts.postthreadid = ? OR posts.postid = ? ORDER BY posts.posttimestamp";

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

    public ArrayList<Post> getThread(int threadid) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement st = con.prepareStatement(GET_THREAD_QUERY);
        
        st.setInt(1, threadid);
        st.setInt(2, threadid);
        
        ResultSet rs = st.executeQuery();
        
        while (rs.next()) {
            int postid = rs.getInt("postid");
            String posttype = rs.getString("posttype");
            int postparentid = rs.getInt("postparentid");
            long posttimestamp = rs.getLong("posttimestamp");
            int authorid = rs.getInt("postauthorid");
            int postthreadid = rs.getInt("postthreadid");
            String postcontent = rs.getString("postcontent");

            Post tmp = new Post(postid, posttype, postparentid, posttimestamp, authorid, postthreadid, postcontent);
            ret.add(tmp);
        }

        con.close();

        return ret;
    }
}
