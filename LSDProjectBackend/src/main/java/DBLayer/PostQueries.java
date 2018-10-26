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

    private static final String INSERT_STORY_QUERY = "INSERT INTO posts VALUES (?, ?, NULL, ?, NULL, ?)";
    private static final String INSERT_COMMENT_QUERY = "INSERT INTO posts VALUES (?, ?, ?, ?, ?, ?)";
    private static final String GET_THREAD_QUERY = "SELECT * FROM posts WHERE posts.postthreadid = ? OR posts.postid = ? ORDER BY posts.postid";
    private static final String GET_POST_QUERY = "SELECT * FROM posts WHERE postid = ?";
    private static final String GET_MOST_RECENT_POST_QUERY = "SELECT * FROM posts ORDER BY postid DESC LIMIT 1";
    private static final String GET_MOST_RECENT_STORIES_QUERY = "SELECT * FROM posts WHERE posttype = ? ORDER BY postid DESC LIMIT ?";
    private static final String LOOKUP_USER_CREDENTIASL = "SELECT * FROM users WHERE username = ? AND userpassword = ?";

    public enum STATUS {
        OK,
        UNAUTHORIZED,
        MALFORMED_INPUT
    }

    private static final int MAX_FRONTPAGE_STORIES = 10;

    public ArrayList<Post> getMostRecentStories() throws SQLException {
        ArrayList<Post> ret;
        try (Connection con = HikariCPDataSource.getConnection()) {
            ret = new ArrayList<>();
            ResultSet rs;
            PreparedStatement st = con.prepareStatement(GET_MOST_RECENT_STORIES_QUERY);
            st.setString(1, "story");
            st.setInt(2, MAX_FRONTPAGE_STORIES);
            rs = st.executeQuery();
            while (rs.next()) {
                Post tmp = getPostFromResultSet(rs);
                ret.add(tmp);
            }
        }

        return ret;
    }

    public STATUS insertStory(Post p, String username, String password) throws SQLException {
        try (Connection con = HikariCPDataSource.getConnection()) {
            int authorid = verifyCredentials(con, username, password);

            if (authorid == 0) {
                return STATUS.UNAUTHORIZED;
            }

            PreparedStatement st = con.prepareStatement(INSERT_STORY_QUERY);

            st.setInt(1, p.postid);
            st.setString(2, p.posttype);
            /* story er root, ingen parent */
            st.setInt(3, authorid);
            /* story er root, ingen threadid */
            st.setString(4, p.postcontent);

            st.execute();
            return STATUS.OK;
        }
    }

    public Post getMostRecentPost() throws SQLException {
        Post ret;
        try (Connection con = HikariCPDataSource.getConnection()) {
            ResultSet rs;
            ret = null;
            PreparedStatement st = con.prepareStatement(GET_MOST_RECENT_POST_QUERY);
            rs = st.executeQuery();
            while (rs.next()) {
                ret = getPostFromResultSet(rs);
            }
        }

        return ret;
    }

    public STATUS insertCommentWithLookup(Post p, String username, String password) throws SQLException {
        PreparedStatement st;
        ResultSet rs;
        boolean found = false;

        try (Connection con = HikariCPDataSource.getConnection()) {
            int authorid = verifyCredentials(con, username, password);
            if (authorid == 0) {
                return STATUS.UNAUTHORIZED;
            }

            st = con.prepareStatement(GET_POST_QUERY);
            st.setInt(1, p.postparentid);
            rs = st.executeQuery();

            while (rs.next()) {
                found = true;
                p.postthreadid = (rs.getString("posttype").equals("story"))
                        ? p.postparentid
                        : rs.getInt("postthreadid");
            }

            if (!found) {
                return STATUS.MALFORMED_INPUT;
            }

            p.postauthorid = authorid;

            insertCommentUsingCon(con, p);
            return STATUS.OK;
        }
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

    private int verifyCredentials(Connection con, String username, String password) throws SQLException {
        PreparedStatement st = con.prepareStatement(LOOKUP_USER_CREDENTIASL);
        ResultSet rs;
        int ret = 0;

        st.setString(1, username);
        st.setString(2, password);

        rs = st.executeQuery();

        while (rs.next()) {
            ret = rs.getInt("userid");
        }

        return ret;
    }

    public ArrayList<Post> getThread(int threadid) throws SQLException {
        ArrayList<Post> ret = new ArrayList<>();
        try (Connection con = HikariCPDataSource.getConnection()) {
            PreparedStatement st = con.prepareStatement(GET_THREAD_QUERY);

            st.setInt(1, threadid);
            st.setInt(2, threadid);

            ResultSet rs = st.executeQuery();

            while (rs.next()) {
                Post tmp = getPostFromResultSet(rs);
                ret.add(tmp);
            }
        }

        return ret;
    }

    private Post getPostFromResultSet(ResultSet rs) throws SQLException {
        int postid = rs.getInt("postid");
        String posttype = rs.getString("posttype");
        int postparentid = rs.getInt("postparentid");
        int authorid = rs.getInt("postauthorid");
        int postthreadid = rs.getInt("postthreadid");
        String postcontent = rs.getString("postcontent");

        Post ret = new Post(postid, posttype, postparentid, authorid, postthreadid, postcontent);
        return ret;
    }
}
