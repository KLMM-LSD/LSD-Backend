/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DBLayer;

import entities.Post;
import entities.User;
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

    private static final String INSERT_STORY_QUERY = "INSERT INTO posts VALUES (?, ?, NULL, ?, NULL, ?)";
    private static final String INSERT_COMMENT_QUERY = "INSERT INTO posts VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_THREAD_QUERY = "SELECT * FROM posts WHERE posts.postthreadid = ? OR posts.postid = ? ORDER BY posts.posttimestamp";
    private static final String GET_POST_QUERY = "SELECT * FROM posts WHERE postid = ?";

    /* 0 er ikke NULL i MySQL */
    public void insertStory(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();

        PreparedStatement st = con.prepareStatement(INSERT_STORY_QUERY);

        st.setInt(1, p.postid);
        st.setString(2, p.posttype);
        /* story er root, ingen parent */
        st.setInt(3, p.postauthorid);
        /* story er root, ingen threadid */
        st.setString(4, p.postcontent);

        st.execute();

        con.close();
    }

    public void insertCommentWithLookup(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        PreparedStatement parent_st = con.prepareStatement(GET_POST_QUERY);
        ResultSet parent_rs;
        boolean found = false;

        parent_st.setInt(1, p.postparentid);
        parent_rs = parent_st.executeQuery();

        while (parent_rs.next()) {
            if (parent_rs.getString("posttype").equals("story")) {
                p.postthreadid = p.postparentid;
                System.out.println("Use parent id as threadid" + p.postthreadid);
            } else {
                p.postthreadid = parent_rs.getInt("postthreadid");
                System.out.println("Use threadid as threaid" + p.postthreadid);
            }
            found = true;
        }

        if (found) {
            PreparedStatement st = con.prepareStatement(INSERT_COMMENT_QUERY);
            insertCommentUsingCon(con, p);
        } else {
            System.out.println("No such parent");
        }

        con.close();
    }

    public void insertComment(Post p) throws SQLException {
        Connection con = HikariCPDataSource.getConnection();
        insertCommentUsingCon(con, p);
        con.close();
    }

    private void insertCommentUsingCon(Connection con, Post p) throws SQLException {
        PreparedStatement st = con.prepareStatement(INSERT_COMMENT_QUERY);

        st.setInt(1, p.postid);
        st.setString(2, p.posttype);
        st.setInt(3, p.postparentid);
        st.setInt(4, p.postauthorid);
        st.setInt(5, p.postthreadid);
        st.setString(6, p.postcontent);

        st.execute();
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
            int authorid = rs.getInt("postauthorid");
            int postthreadid = rs.getInt("postthreadid");
            String postcontent = rs.getString("postcontent");

            Post tmp = new Post(postid, posttype, postparentid, authorid, postthreadid, postcontent);
            ret.add(tmp);
        }

        con.close();

        return ret;
    }

}
