/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package testQueries;

import DBLayer.PostQueries;
import DBLayer.UserQueries;
import entities.Post;
import entities.User;
import java.sql.SQLException;
import java.util.ArrayList;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import org.junit.Test;

/**
 *
 * @author Lasse
 */
public class PostTestIT {

    /* Story
            Reply1
                Reply2
            Reply3
     */
    @Test
    public void insertStory() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        ArrayList<Post> thread;

        Post story = new Post();
        Post reply1 = new Post();
        Post reply2 = new Post();
        Post reply3 = new Post();

        User u, lookup;

        u = new User(-1, "user", "authorone", "authorpwd");
        uq.insertUser(u);

        lookup = uq.getUserByName("authorone");
        assertNotEquals(null, lookup);

        story.initStory(100, lookup.userid, "The story");
        reply1.initComment(101, 100, lookup.userid, "Reply1");
        reply2.initComment(102, 101, lookup.userid, "Reply2");
        reply3.initComment(103, 100, lookup.userid, "Reply3");

        pq.insertStory(story);
        pq.insertCommentWithLookup(reply1);
        pq.insertCommentWithLookup(reply2);
        pq.insertCommentWithLookup(reply3);
        
        thread = pq.getThread(100);

        assertEquals(4, thread.size());
    }

    /* Story
            Reply1
            Reply2
     */
    @Test
    public void insertAnotherStory() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        ArrayList<Post> thread;

        Post story = new Post();
        Post reply1 = new Post();
        Post reply2 = new Post();

        User u, lookup;

        u = new User(-1, "user", "authortwo", "authorpwd");
        uq.insertUser(u);

        lookup = uq.getUserByName("authortwo");
        assertNotEquals(null, lookup);

        story.initStory(200, lookup.userid, "The second story");
        reply1.initComment(201, 200, lookup.userid, "Reply1 to story2");
        reply2.initComment(202, 200, lookup.userid, "Reply2 to story2");

        pq.insertStory(story);
        pq.insertCommentWithLookup(reply1);
        pq.insertCommentWithLookup(reply2);

        thread = pq.getThread(200);

        assertEquals(3, thread.size());
    }
    
    @Test
    public void getMostRecentTest() throws SQLException
    {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        User u = new User(-1, "user", "authorthree", "authorpwd");
        User lookup;
        Post p = new Post();
        Post recentLookup;
        
        uq.insertUser(u);
        
        lookup = uq.getUserByName("authorthree");
        p.initStory(300, lookup.userid, "Story three");
        
        pq.insertStory(p);
        
        recentLookup = pq.getMostRecentPost();
        
        assertEquals(p.postauthorid, recentLookup.postauthorid);
        assertEquals(p.postcontent, recentLookup.postcontent);
    }
}
