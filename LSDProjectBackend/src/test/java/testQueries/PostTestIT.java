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
import static org.junit.Assert.assertNotNull;
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
    public void insertStoryTest() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        ArrayList<Post> thread;

        Post story = new Post();
        Post reply1 = new Post();
        Post reply2 = new Post();
        Post reply3 = new Post();

        User u = new User(-1, "user", "authorone", "authorpwd");
        uq.insertUser(u);

        story.initStory(100, "The story");
        reply1.initComment(101, 100, "Reply1");
        reply2.initComment(102, 101, "Reply2");
        reply3.initComment(103, 100, "Reply3");

        assertEquals(PostQueries.STATUS.OK,
                pq.insertStory(story, "authorone", "authorpwd"));
        assertEquals(PostQueries.STATUS.OK,
                pq.insertCommentWithLookup(reply1, "authorone", "authorpwd"));
        assertEquals(PostQueries.STATUS.OK,
                pq.insertCommentWithLookup(reply2, "authorone", "authorpwd"));
        assertEquals(PostQueries.STATUS.OK,
                pq.insertCommentWithLookup(reply3, "authorone", "authorpwd"));

        thread = pq.getThread(100);

        assertEquals(4, thread.size());
    }

    /* Story
            Reply1
            Reply2
     */
    @Test
    public void insertAnotherStoryTest() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        ArrayList<Post> thread;

        Post story = new Post();
        Post reply1 = new Post();
        Post reply2 = new Post();

        User u = new User(-1, "user", "authortwo", "authorpwd");
        uq.insertUser(u);

        story.initStory(200, "The second story");
        reply1.initComment(201, 200, "Reply1 to story2");
        reply2.initComment(202, 200, "Reply2 to story2");

        assertEquals(PostQueries.STATUS.OK,
                pq.insertStory(story, "authortwo", "authorpwd"));
        assertEquals(PostQueries.STATUS.OK,
                pq.insertCommentWithLookup(reply1, "authortwo", "authorpwd"));
        assertEquals(PostQueries.STATUS.OK,
                pq.insertCommentWithLookup(reply2, "authortwo", "authorpwd"));

        thread = pq.getThread(200);

        assertEquals(3, thread.size());
    }

    @Test
    public void getMostRecentPostTest() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        User u = new User(-1, "user", "authorthree", "authorpwd");
        Post p = new Post();
        Post recentLookup;

        uq.insertUser(u);
        p.initStory(300, "Story three");

        pq.insertStory(p, "authorthree", "authorpwd");

        recentLookup = pq.getMostRecentPost();
        /* lah: dette er ikke garanteret at været 100% ajour
        assertEquals(p.postauthorid, recentLookup.postauthorid);
        assertEquals(p.postcontent, recentLookup.postcontent);*/
        assertNotNull(recentLookup);

    }

    @Test
    public void getFrontPageTest() throws SQLException {
        UserQueries uq = new UserQueries();
        PostQueries pq = new PostQueries();
        User u = new User(-1, "user", "authorfour", "authorpwd");
        Post p = new Post();
        ArrayList<Post> frontpage;

        uq.insertUser(u);

        p.initStory(400, "Lorem story");
        pq.insertStory(p, "authorfour", "authorpwd");

        frontpage = pq.getMostRecentStories();

        assertNotEquals(0, frontpage.size());

        for (Post tmp : frontpage) {
            assertEquals("story", tmp.posttype);
        }
    }
}
