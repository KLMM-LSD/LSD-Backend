///*
// * To change this license header, choose License Headers in Project Properties.
// * To change this template file, choose Tools | Templates
// * and open the template in the editor.
// */
//package testQueries;
//
//import DBLayer.PostQueries;
//import entities.Posts;
//import entities.Users;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.List;
//import org.junit.AfterClass;
//import static org.junit.Assert.assertTrue;
//import org.junit.Before;
//import org.junit.BeforeClass;
//import org.junit.Ignore;
//import org.junit.Test;
//
///**
// *
// * @author Mart_
// */
//public class PostQueriesIT {
//    
//    private static PostQueries pq;
//    private static Posts testPost;
//    private static Users testUser;
//    
//    public PostQueriesIT() {
//    
//    }
//    
//    @BeforeClass
//    public static void setUpClass() throws SQLException {
//   
//    pq = new PostQueries();
//    pq.setUp();
//    testUser = new Users(1001, "user", System.currentTimeMillis(), "AuthorGuy", "123", "Witty and Clever guy");
//    testPost = new Posts(9919, "story",3,System.currentTimeMillis(),2,"How Mirrors Be Real, If Our Eyes Aren't? - Jaden Smith");
//    
//    
//    }
//    
//    @AfterClass
//    public static void tearDownClass() {
//        //pq.deletePost(id); 
//    }
//    
//    @Before
//    public void setUp() {
//    }
//    
//    @Test
//    public void createPostTest() throws SQLException{
//        int sumOfPosts = pq.sumOfPosts();
//        pq.createPost(testPost);
//        System.out.println(sumOfPosts);
//        assertTrue(sumOfPosts > /*pq.sumOfPosts()*/ pq.sumOfPosts());
//        
//    }
//    
//    @Ignore
//    public void userPostsTest() throws SQLException{
//        List<Posts> userPosts = new ArrayList();
//        userPosts = pq.userPosts(1001);
//        assertTrue(userPosts.size() > 0);
//    }
//}
