/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

/**
 *
 * @author Lasse
 */
public class Post {

    public int postid;
    public String posttype;
    public int postparentid;
    public int postauthorid;
    public int postthreadid;
    public String postcontent;

    public void initStory(int postid, int postauthorid, String postcontent) {
        this.postid = postid;
        this.posttype = "story";
        /* this.postparentid = NULL; */
        this.postauthorid = postauthorid;
        /* this.postthreadid = NULL; */
        this.postcontent = postcontent;
    }
    
    public void initComment(int postid, int postparentid, int postauthorid, String postcontent)
    {
        this.postid = postid;
        this.posttype = "comment";
        this.postparentid = postparentid;
        this.postauthorid = postauthorid;
        /* this.postthreadid = NULL; */
        this.postcontent = postcontent;
    }

    public Post(int postid, String posttype, int postparentid, int postauthorid, int postthreadid, String postcontent) {
        this.postid = postid;
        this.posttype = posttype;
        this.postparentid = postparentid;
        this.postauthorid = postauthorid;
        this.postthreadid = postthreadid;
        this.postcontent = postcontent;
    }

    public Post() {
    }

}
