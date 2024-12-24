package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PinnedPosting")
@IdClass(PinnedPostingId.class)
public class PinnedPosting {
    
    @Id
    @Column(name = "userid")
    private int userID;

    @Id
    @Column(name = "postid")
    private int postID;
    @Column(name = "pinneddate")
    private Date pinnedDate;

    public Date getPinnedDate() {
        return pinnedDate;
    }

    public void setPinnedDate(Date pinnedDate) {
        this.pinnedDate = pinnedDate;
    }

    public int getUserID() {
        return userID;
    }

    public int getPostID() {
        return postID;
    }
}
