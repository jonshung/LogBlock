package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "essmedia")
@IdClass(ESSMediaId.class)
public class ESSMedia {

    @Id
    @Column(name = "postid")
    private int postID;
    @Id
    @Column(name = "solutionid")
    private int solutionID;
    @Id
    @Column(name = "solutionmediaid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int solutionMediaID;

    @Column(name = "solutionmediauri")
    private String solutionMediaURI;

    /**
     * 
     * @param postID
     * @param solutionID
     * @param solutionMediaURI
     */
    public ESSMedia(int postID, int solutionID, String solutionMediaURI) {
        this.postID = postID;
        this.solutionID = solutionID;
        this.solutionMediaURI = solutionMediaURI;
    }

    public ESSMedia() {
        
    }

    public int getPostID() {
        return postID;
    }

    public int getSolutionID() {
        return solutionID;
    }

    public int getSolutionMediaID() {
        return solutionMediaID;
    }

    public String getSolutionMediaURI() {
        return solutionMediaURI;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setSolutionID(int solutionID) {
        this.solutionID = solutionID;
    }

    public void setSolutionMediaID(int solutionMediaID) {
        this.solutionMediaID = solutionMediaID;
    }

    public void setSolutionMediaURI(String solutionMediaURI) {
        this.solutionMediaURI = solutionMediaURI;
    }

}
