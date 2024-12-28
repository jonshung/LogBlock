package com.logblock.backend.DataSource.DTO;

import com.logblock.backend.DataSource.Model.PostingMedia;

public class PostingMediaDTO {
    
    public int mediaID;
    public int postID;
    public String mediaURI;

    public PostingMediaDTO(int mediaID, int postID, String mediaURI) {
        this.mediaID = mediaID;
        this.postID = postID;
        this.mediaURI = mediaURI;
    }

    public static PostingMedia toPostingMedia(PostingMediaDTO dto) {
        PostingMedia p = new PostingMedia(dto.postID, dto.mediaURI);
        if(dto.mediaID > 0) {
            p.setMediaID(dto.mediaID);
        }
        return p;
    }

    public static PostingMediaDTO toDTO(PostingMedia p) {
        return new PostingMediaDTO(p.getMediaID(), p.getPostID(), p.getMediaURI());
    }
}
