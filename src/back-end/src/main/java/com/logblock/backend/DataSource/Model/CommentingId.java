package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class CommentingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int commentID;

    public CommentingId() {
        this.postID = 0;
        this.commentID = 0;
    }

    public CommentingId(int postID, int commentID) {
        this.postID = postID;
        this.commentID = commentID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + commentID;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;

        CommentingId other = (CommentingId) obj;
        return (postID == other.postID) && (commentID == other.commentID);
    }
}
