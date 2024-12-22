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
@Table( name = "Commenting" )
@IdClass(CommentingId.class)
public class Commenting {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "commentID")
    private int commentID;

    @Id
    @Column(name = "postID")
    private int postID;
    @Column(name = "commentAuthor")
    private int authorID;
    @Column(name = "commentCaption")
    private String caption;
    @Column(name = "commentCreation")
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
    public void setPostID(int newPostID) {
        this.postID = newPostID;
    }

    public void setAuthorID(int newAuthorID) {
        this.authorID = newAuthorID;
    }

    public void setCaption(String newCaption) {
        this.caption = newCaption;
    }

    public void setCreationDate(Date newCreationDate) {
        this.creationDate = newCreationDate;
    }
}
