package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "ESS")
@IdClass(ESSId.class)
public class ExpertSuggestedSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "solutionid")
    private int solutionID;

    @Id
    @Column(name = "postid")
    private int postID;
    @Column(name = "solutionauthor")
    private int solutionAuthor;
    @Column(name = "solutioncaption")
    private String solutionCaption;

    @Column(name = "solutioncreation")
    private Date solutionCreationDate;
    @Column(name = "solutionlastupdate")
    private Date solutionLastModifiedDate;

    /**
     * 
     * @param postID
     * @param solutionAuthor
     * @param solutionCaption
     * @param solutionCreationDate
     * @param solutionLastModifiedDate
     */
    public ExpertSuggestedSolution(int postID, int solutionAuthor, String solutionCaption,
            Date solutionCreationDate, Date solutionLastModifiedDate) {
        this.postID = postID;
        this.solutionAuthor = solutionAuthor;
        this.solutionCaption = solutionCaption;
        this.solutionCreationDate = solutionCreationDate;
        this.solutionLastModifiedDate = solutionLastModifiedDate;
    }

    public ExpertSuggestedSolution() {
        
    }

    // Getters
    public int getSolID() {
        return solutionID;
    }

    public int getPostID() {
        return postID;
    }

    public int getSolAuthor() {
        return solutionAuthor;
    }

    public String getSolCaption() {
        return solutionCaption;
    }

    public Date getSolCreationDate() {
        return solutionCreationDate;
    }

    public Date getSolLastModifiedDate() {
        return solutionLastModifiedDate;
    }

    // Setters
    public void setSolAuthor(int newSolAuthor) {
        this.solutionAuthor = newSolAuthor;
    }

    public void setSolutionID(int solutionID) {
        this.solutionID = solutionID;
    }

    public void setSolCaption(String newSolCaption) {
        this.solutionCaption = newSolCaption;
    }

    public void setSolCreationDate(Date newSolCreationDate) {
        this.solutionCreationDate = newSolCreationDate;
    }

    public void setSolLastModifiedDate(Date newSolLastModifiedDate) {
        this.solutionLastModifiedDate = newSolLastModifiedDate;
    }
}
