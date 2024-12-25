package com.logblock.backend.DataSource.DTO;

import java.util.Date;

import com.logblock.backend.DataSource.Model.Posting;

public class PostingDTO {
    public int postID;
    public int originalAuthor;
    public String caption;
    public Date creationDate;
    public Date lastModifiedDate;
    
    public PostingDTO(int postID, int originalAuthor, String caption, Date creationDate, Date lastModifiedDate) {
        this.postID = postID;
        this.originalAuthor = originalAuthor;
        this.caption = caption;
        this.creationDate = creationDate;
        this.lastModifiedDate = lastModifiedDate;
    }

    public static Posting toPosting(PostingDTO dto) {
        Posting p = new Posting(dto.originalAuthor, dto.caption, dto.creationDate, dto.lastModifiedDate);
        if(dto.postID > 0) p.setPostID(dto.postID);
        return p;
    }

    public static PostingDTO toDTO(Posting p) {
        return new PostingDTO(p.getPostID(), p.getAuthorID(), p.getCaption(), p.getCreationDate(), p.getLastModifiedDate());
    }
}
