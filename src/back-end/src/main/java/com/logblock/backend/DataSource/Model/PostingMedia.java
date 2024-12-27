package com.logblock.backend.DataSource.Model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "postingmedia")
@IdClass(PostingMediaId.class)
public class PostingMedia {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mediaid")
    private int mediaID;

    @Id
    @Column(name = "postid")
    private int postID;

    @Column(name = "mediauri")
    private String mediaURI;

    /**
     * 
     * @param postID
     * @param mediaURI
     */
    public PostingMedia(int postID, String mediaURI) {
        this.postID = postID;
        this.mediaURI = mediaURI;
    }

    public PostingMedia() {
        
    }

    // Getters
    public int getMediaID() {
        return mediaID;
    }

    public int getPostID() {
        return postID;
    }

    public String getCommentCaption() {
        return mediaURI;
    }

    public void setMediaURI(String newURI) {
        this.mediaURI = newURI;
    }

    public void setMediaID(int mediaID) {
        this.mediaID = mediaID;
    }

    public void setPostID(int postID) {
        this.postID = postID;
    }
    
}
