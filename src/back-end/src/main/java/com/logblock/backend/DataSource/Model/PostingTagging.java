package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "PostingTagging")
@IdClass(PostingTaggingId.class)
public class PostingTagging {

    @Id
    @Column(name = "postID")
    private int postID;
    @Id
    @Column(name = "tagTargetID")
    private int tagTargetID;

    public int getPostID() {
        return postID;
    }

    public int getTagTargetID() {
        return tagTargetID;
    }
}
