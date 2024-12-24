package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PostingUpvote")
@IdClass(PostingUpvoteId.class)
public class PostingUpvote {

    @Id
    @Column(name = "postid")
    private int postID;
    @Id
    @Column(name = "upvoterid")
    private int upvoterID;

    @Column(name = "upvotedate")
    private Date upvoteDate;

    /**
     * 
     * @param postID
     * @param upvoterID
     * @param upvoteDate
     */
    public PostingUpvote(int postID, int upvoterID, Date upvoteDate) {
        this.postID = postID;
        this.upvoterID = upvoterID;
        this.upvoteDate = upvoteDate;
    }

    public PostingUpvote() {
        
    }

    public int getPostID() {
        return postID;
    }

    public int getUpvoterID() {
        return upvoterID;
    }

    public Date getUpvoteDate() {
        return upvoteDate;
    }

    public void setUpvoteDate(Date upvoteDate) {
        this.upvoteDate = upvoteDate;
    }
}