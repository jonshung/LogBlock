package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class PinnedPostingId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int userID;
    private final int postID;

    public PinnedPostingId() {
        this.userID = 0;
        this.postID = 0;
    }

    public PinnedPostingId(int userID, int postID) {
        this.userID = userID;
        this.postID = postID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userID;
        result = prime * result + postID;
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

        PinnedPostingId other = (PinnedPostingId) obj;
        return (userID == other.postID) && (postID == other.postID);
    }
}
