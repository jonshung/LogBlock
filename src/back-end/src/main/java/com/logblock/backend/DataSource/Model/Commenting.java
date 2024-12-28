package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

@Entity
@Table( name = "Commenting" )
@IdClass(CommentingId.class)
public class Commenting {

    @Id
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator="commenting_generator"
    )
    @SequenceGenerator(name = "commenting_generator", sequenceName="commenting_seq", allocationSize = 1) 
    private int commentID;

    @Id
    @Column(name = "postid")
    private int postID;

    @Column(name = "commentauthor")
    private int authorID;
    @Column(name = "commentcaption")
    private String caption;
    @Column(name = "commentcreation")
    private Date creationDate;
    
    /**
     * 
     * @param postID
     * @param authorID
     * @param caption
     * @param creationDate
     */
    public Commenting(int postID, int authorID, String caption, Date creationDate) {
        this.postID = postID;
        this.authorID = authorID;
        this.caption = caption;
        this.creationDate = creationDate;
    }

    public Commenting() {
        
    }

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

    public void setCommentID(int commentID) {
        this.commentID = commentID;
    }
}
