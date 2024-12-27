package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "postingtagging")
@IdClass(PostingTaggingId.class)
public class PostingTagging {

    @Id
    @Column(name = "postid")
    private int postID;
    @Id
    @Column(name = "tagTargetid")
    private int tagTargetID;

    /**
     * 
     * @param postID
     * @param tagTargetID
     */
    public PostingTagging(int postID, int tagTargetID) {
        this.postID = postID;
        this.tagTargetID = tagTargetID;
    }

    public PostingTagging() {
        
    }

    public int getPostID() {
        return postID;
    }

    public int getTagTargetID() {
        return tagTargetID;
    }
}
