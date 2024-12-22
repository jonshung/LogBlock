package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class PostingTaggingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int tagTargetID;

    public PostingTaggingId() {
        this.postID = 0;
        this.tagTargetID = 0;
    }

    public PostingTaggingId(int postID, int tagTargetID) {
        this.postID = postID;
        this.tagTargetID = tagTargetID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + tagTargetID;
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

        PostingTaggingId other = (PostingTaggingId) obj;
        return (postID == other.postID) && (tagTargetID == other.tagTargetID);
    }
}
