package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class PostingMediaId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int mediaID;

    public PostingMediaId() {
        this.postID = 0;
        this.mediaID = 0;
    }

    public PostingMediaId(int postID, int mediaID) {
        this.postID = postID;
        this.mediaID = mediaID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + mediaID;
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

        PostingMediaId other = (PostingMediaId) obj;
        return (postID == other.postID) && (mediaID == other.mediaID);
    }
}
