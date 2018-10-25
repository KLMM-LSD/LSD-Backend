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
    public long posttimestamp;
    public int postauthorid;
    public int postthreadid;
    public String postcontent;

    public Post(int postid, String posttype, int postparentid, long posttimestamp, int postauthorid, int postthreadid, String postcontent) {
        this.postid = postid;
        this.posttype = posttype;
        this.postparentid = postparentid;
        this.posttimestamp = posttimestamp;
        this.postauthorid = postauthorid;
        this.postthreadid = postthreadid;
        this.postcontent = postcontent;
    }

    public Post(int postid, String posttype, long posttimestamp, int postauthorid, String postcontent) {
        this.postid = postid;
        this.posttype = posttype;
        this.posttimestamp = posttimestamp;
        this.postauthorid = postauthorid;
        this.postcontent = postcontent;
    }
   

}
