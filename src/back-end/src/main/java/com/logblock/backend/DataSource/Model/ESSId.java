package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class ESSId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int solutionID;

    public ESSId() {
        this.postID = 0;
        this.solutionID = 0;
    }

    public ESSId(int postID, int solutionID) {
        this.postID = postID;
        this.solutionID = solutionID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + solutionID;
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

        ESSId other = (ESSId) obj;
        return (postID == other.postID) && (solutionID == other.solutionID);
    }
}