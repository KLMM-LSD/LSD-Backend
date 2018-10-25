///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package DBLayer;
//
//import entities.Posts;
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//import java.sql.Statement;
//import java.util.ArrayList;
//import java.util.List;
//import java.util.logging.Level;
//import java.util.logging.Logger;
//import javax.annotation.PostConstruct;
//import javax.json.JsonObject;
//
///**
// *
// * @author Mart_
// */
//public class PostQueries {
//
//    private DatabaseAccess access;
//    private Connection connection;
//
//    public void setUp() throws SQLException {
//        if (access == null) {
//            access = new DatabaseAccess();
//        }
//        if (connection == null) {
//            try {
//                connection = access.getConnection();
//            } catch (SQLException ex) {
//                System.out.println("Men Der sket en fejl");
//                Logger.getLogger(UserQueries.class.getName()).log(Level.SEVERE, null, ex);
//            }
//        }
//    }
//
//    // Måske postThreadId
//    public Posts createPost(Posts post) throws SQLException {
//        System.out.println("Nu Laver jeg en post");
//        Posts p = new Posts();
//        setUp();
//        PreparedStatement pstmt = connection.prepareStatement("INSERT INTO posts (postid, posttype, postparentid, posttimestamp, postauthorid, postcontent)"
//                + "VALUES (?,?,?,?,?,?);");
//        pstmt.setInt(1, p.getPostid());
//        pstmt.setString(2, p.getPosttype());
//        pstmt.setInt(3, p.getPostauthorid());
//        pstmt.setLong(4, System.currentTimeMillis());
//        pstmt.setInt(5, p.getPostauthorid());
//        pstmt.setString(6, p.getPostcontent());
//        pstmt.execute();
//        return post;
//    }
//
//    // Skal timestamp opdateres for at reflektere redigeringstidspunktet? 
//    public void updatePost(JsonObject js) throws SQLException {
//        PreparedStatement pstmt = connection.prepareStatement("UPDATE posts SET post_text = ?"
//                + "WHERE postid = ?;");
//        pstmt.setString(1, js.getString("post_text"));
//        pstmt.setInt(2, js.getInt("post_id"));
//        pstmt.execute();
//    }
//
//    public List<Posts> userPosts(int userid) throws SQLException {
//        PreparedStatement pstmt = connection.prepareStatement("SELECT postid, posttype, posttimestamp, postcontent FROM posts WHERE postauthorid = ?");
//        pstmt.setInt(1, userid);
//        List<Posts> posts = new ArrayList();
//        ResultSet rs = pstmt.executeQuery();
//        if (!rs.next()) {
//            //Users may have no posts, so this is not an issue
//        } else {
//            do {
//                Posts post = new Posts(rs.getInt("postid"), rs.getString("posttype"), rs.getInt("postparentid"), rs.getLong("posttimestamp"), rs.getInt("postauthorid"), rs.getString("postcontent"));
//                posts.add(post);
//            } while (rs.next());
//        }
//
//        return posts;
//    }
//
//    public List<Posts> postChildren(int postid) throws SQLException {
//        PreparedStatement pstmt = connection.prepareStatement("SELECT postid, posttype, posttimestamp, postcontent FROM posts WHERE postparentid = ?");
//        pstmt.setInt(1, postid);
//        List<Posts> posts = new ArrayList();
//        ResultSet rs = pstmt.executeQuery();
//        if (!rs.next()) {
//            //Posts may have no children
//        } else {
//            do {
//                posts.add(new Posts(rs.getInt("postid"), rs.getString("posttype"), rs.getInt("postparentid"), rs.getLong("posttimestamp"), rs.getInt("postauthorid"), rs.getString("postcontent")));
//                //Set postparentid here. (Should be postparent?)
//            } while (rs.next());
//        }
//        return null;
//    }
//
//    public int sumOfPosts() throws SQLException {
//        int count = 0;
//        Statement stmt = connection.createStatement();
//        String query = "SELECT * FROM posts;";
//        ResultSet rs = stmt.executeQuery(query);
//        while (rs.next()) {
//            count++;
//        }
//        return count;
//    }
//
//}
