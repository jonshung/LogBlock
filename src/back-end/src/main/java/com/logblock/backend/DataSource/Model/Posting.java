package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "Posting")
public class Posting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "postid")
    private int postID;

    @Column(name = "originalauthor")
    private int originalAuthor;
    @Column(name = "postcaption")
    private String caption;

    @Column(name = "postcreation")
    private Date creationDate;
    @Column(name = "postlastupdate")
    private Date lastModifiedDate;

    /**
     *
     * @param originalAuthor
     * @param caption
     * @param creationDate
     * @param lastModifiedDate
     */
    public Posting(int originalAuthor, String caption, Date creationDate, Date lastModifiedDate) {
        this.originalAuthor = originalAuthor;
        this.caption = caption;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public Posting(int postID, String caption, int originalAuthor) {
        this.postID = postID;
        this.caption = caption;
        this.originalAuthor = originalAuthor;
        this.creationDate = new Date();
        this.lastModifiedDate = new Date();
    }

    public Posting() {

    }

    // Getters
    public int getPostID() {
        return postID;
    }

    public int getAuthorID() {
        return originalAuthor;
    }

    public String getCaption() {
        return caption;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    // Setters
    public void setAuthorID(int id) {
        this.originalAuthor = id;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }

    public void setCaption(String newCaption) {
        this.caption = newCaption;
    }

    public void setCreationDate(Date newCreationDate) {
        this.creationDate = newCreationDate;
    }

    public void setLastModifiedDate(Date newLastModifiedDate) {
        this.lastModifiedDate = newLastModifiedDate;
    }
}
