package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int commentID;

    private int postID;
    private int authorID;
    private String caption;
    private Date creationDate;

    // Getters
    public int getCommentID() {
        return commentID;
    }

    public int getPostID() {
        return postID;
    }

    public int getCommentAuthorID() {
        return authorID;
    }

    public String getCommentCaption() {
        return caption;
    }

    public Date getCommentCreationDate() {
        return creationDate;
    }

    // Setters
    public void setAuthorID(int newAuthorID) {
        this.authorID = newAuthorID;
    }

    public void setCaption(String newCaption) {
        this.caption = newCaption;
    }

    public void setCreationDate(Date newCreationDate) {
        this.creationDate = newCreationDate;
    }

    public void setPostID(int newPostID){
        this.postID = newPostID;
    }
}
