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
        reply2.initComment(102, 101, lookup.userid, "Reply 2");
        reply2.initComment(103, 100, lookup.userid, "Reply 3");
        
        pq.insertStory(story);
        pq.insertCommentWithLookup(reply1);
        pq.insertCommentWithLookup(reply2);
        pq.insertCommentWithLookup(reply3);
    }
}
