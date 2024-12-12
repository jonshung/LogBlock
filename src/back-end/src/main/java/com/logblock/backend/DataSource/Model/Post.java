package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
public class Post {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int postID;

    private int authorID;
    private String caption;

    @ElementCollection
    private List<String> media;

    private Date creationDate;
    private Date lastModifiedDate;

    @ElementCollection
    private List<Integer> tags;

    @ElementCollection
    private List<Integer> upvoters;

    // Getters
    public int getPostID() {
        return postID;
    }

    public int getAuthorID() {
        return authorID;
    }

    public String getCaption() {
        return caption;
    }

    public List<String> getMedia() {
        return media;
    }

    public Date getCreationDate() {
        return creationDate;
    }

    public Date getLastModifiedDate() {
        return lastModifiedDate;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public List<Integer> getUpvoters() {
        return upvoters;
    }

    // Setters
    public void setPAuthorID(int newAuthorID) {
        this.authorID = newAuthorID;
    }

    public void setCaption(String newCaption) {
        this.caption = newCaption;
    }

    public void setMedia(List<String> newMedia) {
        this.media = newMedia;
    }

    public void setCreationDate(Date newCreationDate) {
        this.creationDate = newCreationDate;
    }

    public void setLastModifiedDate(Date newLastModifiedDate) {
        this.lastModifiedDate = newLastModifiedDate;
    }

    public void setTags(List<Integer> tagsList) {
        this.tags = tagsList;
    }

    public void setUpvoters(List<Integer> newUpvotersList) {
        this.upvoters = newUpvotersList;
    }
}
