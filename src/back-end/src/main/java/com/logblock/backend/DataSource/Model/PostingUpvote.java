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
    @Column(name = "postID")
    private int postID;
    @Id
    @Column(name = "upvoterID")
    private int upvoterID;

    @Column(name = "upvoteDate")
    private Date upvoteDate;

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