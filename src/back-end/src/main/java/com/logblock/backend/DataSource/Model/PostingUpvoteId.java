package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class PostingUpvoteId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int upvoterID;

    public PostingUpvoteId() {
        this.postID = 0;
        this.upvoterID = 0;
    }

    public PostingUpvoteId(int postID, int upvoterID) {
        this.postID = postID;
        this.upvoterID = upvoterID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + upvoterID;
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

        PostingUpvoteId other = (PostingUpvoteId) obj;
        return (postID == other.postID) && (upvoterID == other.upvoterID);
    }
}
