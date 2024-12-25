package com.logblock.backend.DataSource.DTO;

import java.util.Date;

import com.logblock.backend.DataSource.Model.Commenting;

public class CommentingDTO {
    public int commentID;
    public int postID;
    public int authorID;
    public String caption;
    public Date creationDate;

    public CommentingDTO(int commentID, int postID, int authorID, String caption, Date creationDate) {
        this.commentID = commentID;
        this.postID = postID;
        this.authorID = authorID;
        this.caption = caption;
        this.creationDate = creationDate;
    }
    
    public static Commenting toCommenting(CommentingDTO dto) {
        Commenting c = new Commenting(dto.postID, dto.authorID, dto.caption, dto.creationDate);
        if(dto.commentID > 0) c.setPostID(dto.postID);
        return c;
    }

    public static CommentingDTO toDTO(Commenting c) {
        return new CommentingDTO(c.getCommentID(), c.getPostID(), c.getCommentAuthorID(), c.getCommentCaption(), c.getCommentCreationDate());
    }
}
