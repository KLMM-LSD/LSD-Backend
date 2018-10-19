/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author Micha
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p")
    , @NamedQuery(name = "Posts.findByPostid", query = "SELECT p FROM Posts p WHERE p.postid = :postid")
    , @NamedQuery(name = "Posts.findByPosttype", query = "SELECT p FROM Posts p WHERE p.posttype = :posttype")
    , @NamedQuery(name = "Posts.findByPosttimestamp", query = "SELECT p FROM Posts p WHERE p.posttimestamp = :posttimestamp")
    , @NamedQuery(name = "Posts.findByPostcontent", query = "SELECT p FROM Posts p WHERE p.postcontent = :postcontent")})
public class Posts implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "postid")
    private Integer postid;
    @Basic(optional = false)
    @Column(name = "posttype")
    private String posttype;
    @Basic(optional = false)
    @Column(name = "posttimestamp")
    private long posttimestamp;
    @Basic(optional = false)
    @Column(name = "postcontent")
    private String postcontent;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ratingpostid")
    private Collection<Ratings> ratingsCollection;
    @OneToMany(mappedBy = "postparentid")
    private Collection<Posts> postsCollection;
    @JoinColumn(name = "postparentid", referencedColumnName = "postid")
    @ManyToOne
    private int postparentid;
    @OneToMany(mappedBy = "postthreadid")
    private Collection<Posts> postsCollection1;
    @JoinColumn(name = "postthreadid", referencedColumnName = "postid")
    @ManyToOne
    private Posts postthreadid;
    @JoinColumn(name = "postauthorid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private int postauthorid;

    public Posts() {
    }

    public Posts(Integer postid) {
        this.postid = postid;
    }

    public Posts(Integer postid, String posttype,int postparentid, long posttimestamp, int postauthorid, String postcontent) {
        this.postid = postid;
        this.posttype = posttype;
        this.postparentid = postparentid;
        this.posttimestamp = posttimestamp;
        this.postauthorid = postauthorid;
        this.postcontent = postcontent;
    }

    public Posts(String posttype, long posttimestamp, String postcontent) {
        this.posttype = posttype;
        this.posttimestamp = posttimestamp;
        this.postcontent = postcontent;
    }
    
    public Integer getPostid() {
        return postid;
    }

    public void setPostid(Integer postid) {
        this.postid = postid;
    }

    public String getPosttype() {
        return posttype;
    }

    public void setPosttype(String posttype) {
        this.posttype = posttype;
    }

    public long getPosttimestamp() {
        return posttimestamp;
    }

    public void setPosttimestamp(long posttimestamp) {
        this.posttimestamp = posttimestamp;
    }

    public String getPostcontent() {
        return postcontent;
    }

    public void setPostcontent(String postcontent) {
        this.postcontent = postcontent;
    }

    @XmlTransient
    public Collection<Ratings> getRatingsCollection() {
        return ratingsCollection;
    }

    public void setRatingsCollection(Collection<Ratings> ratingsCollection) {
        this.ratingsCollection = ratingsCollection;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection() {
        return postsCollection;
    }

    public void setPostsCollection(Collection<Posts> postsCollection) {
        this.postsCollection = postsCollection;
    }

    public int getPostparentid() {
        return postparentid;
    }

    public void setPostparentid(int postparentid) {
        this.postparentid = postparentid;
    }

    @XmlTransient
    public Collection<Posts> getPostsCollection1() {
        return postsCollection1;
    }

    public void setPostsCollection1(Collection<Posts> postsCollection1) {
        this.postsCollection1 = postsCollection1;
    }

    public Posts getPostthreadid() {
        return postthreadid;
    }

    public void setPostthreadid(Posts postthreadid) {
        this.postthreadid = postthreadid;
    }

    public int getPostauthorid() {
        return postauthorid;
    }

    public void setPostauthorid(int postauthorid) {
        this.postauthorid = postauthorid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (postid != null ? postid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.postid == null && other.postid != null) || (this.postid != null && !this.postid.equals(other.postid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Posts[ postid=" + postid + " ]";
    }
    
}
