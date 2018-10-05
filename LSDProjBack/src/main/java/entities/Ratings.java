/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Micha
 */
@Entity
@Table(name = "ratings")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ratings.findAll", query = "SELECT r FROM Ratings r")
    , @NamedQuery(name = "Ratings.findByRatingid", query = "SELECT r FROM Ratings r WHERE r.ratingid = :ratingid")
    , @NamedQuery(name = "Ratings.findByRatingvalue", query = "SELECT r FROM Ratings r WHERE r.ratingvalue = :ratingvalue")})
public class Ratings implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ratingid")
    private Integer ratingid;
    @Basic(optional = false)
    @Column(name = "ratingvalue")
    private int ratingvalue;
    @JoinColumn(name = "ratingauthorid", referencedColumnName = "userid")
    @ManyToOne(optional = false)
    private Users ratingauthorid;
    @JoinColumn(name = "ratingpostid", referencedColumnName = "postid")
    @ManyToOne(optional = false)
    private Posts ratingpostid;

    public Ratings() {
    }

    public Ratings(Integer ratingid) {
        this.ratingid = ratingid;
    }

    public Ratings(Integer ratingid, int ratingvalue) {
        this.ratingid = ratingid;
        this.ratingvalue = ratingvalue;
    }

    public Integer getRatingid() {
        return ratingid;
    }

    public void setRatingid(Integer ratingid) {
        this.ratingid = ratingid;
    }

    public int getRatingvalue() {
        return ratingvalue;
    }

    public void setRatingvalue(int ratingvalue) {
        this.ratingvalue = ratingvalue;
    }

    public Users getRatingauthorid() {
        return ratingauthorid;
    }

    public void setRatingauthorid(Users ratingauthorid) {
        this.ratingauthorid = ratingauthorid;
    }

    public Posts getRatingpostid() {
        return ratingpostid;
    }

    public void setRatingpostid(Posts ratingpostid) {
        this.ratingpostid = ratingpostid;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (ratingid != null ? ratingid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ratings)) {
            return false;
        }
        Ratings other = (Ratings) object;
        if ((this.ratingid == null && other.ratingid != null) || (this.ratingid != null && !this.ratingid.equals(other.ratingid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Ratings[ ratingid=" + ratingid + " ]";
    }
    
}
