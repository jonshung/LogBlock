package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class ExpertSuggestedSolution {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int solID;

    private int postID;
    private int solAuthor;
    private String solCaption;

    @ElementCollection
    private List<String> solMedia;

    private Date solCreationDate;
    private Date solLastModifiedDate;

    // Getters
    public int getSolID() {
        return solID;
    }

    public int getPostID() {
        return postID;
    }

    public int getSolAuthor() {
        return solAuthor;
    }

    public String getSolCaption() {
        return solCaption;
    }

    public List<String> getSolMedia() {
        return solMedia;
    }

    public Date getSolCreationDate() {
        return solCreationDate;
    }

    public Date getSolLastModifiedDate() {
        return solLastModifiedDate;
    }

    // Setters
    public void setSolAuthor(int newSolAuthor) {
        this.solAuthor = newSolAuthor;
    }

    public void setSolCaption(String newSolCaption) {
        this.solCaption = newSolCaption;
    }

    public void setSolMedia(List<String> newSolMedia) {
        this.solMedia = newSolMedia;
    }

    public void setSolCreationDate(Date newSolCreationDate) {
        this.solCreationDate = newSolCreationDate;
    }

    public void setSolLastModifiedDate(Date newSolLastModifiedDate) {
        this.solLastModifiedDate = newSolLastModifiedDate;
    }
}
