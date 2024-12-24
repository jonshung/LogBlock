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
    @Column(name="postlastupdate")
    private Date lastModifiedDate;

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
