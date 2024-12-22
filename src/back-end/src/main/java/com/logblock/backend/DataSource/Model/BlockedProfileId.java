package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class BlockedProfileId implements Serializable {
    
    private static final long serialVersionUID = 1L;

    private final int userID;
    private final int targetUserID;

    public BlockedProfileId() {
        this.userID = 0;
        this.targetUserID = 0;
    }

    public BlockedProfileId(int userID, int targetUserID) {
        this.userID = userID;
        this.targetUserID = targetUserID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + userID;
        result = prime * result + targetUserID;
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

        BlockedProfileId other = (BlockedProfileId) obj;
        return (userID == other.userID) && (targetUserID == other.targetUserID);
    }
}
