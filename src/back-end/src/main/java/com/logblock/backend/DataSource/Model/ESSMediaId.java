package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class ESSMediaId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int postID;
    private final int solutionID;
    private final int solutionMediaID;

    public ESSMediaId() {
        this.postID = 0;
        this.solutionID = 0;
        this.solutionMediaID = 0;
    }

    public ESSMediaId(int postID, int solutionID, int solutionMediaID) {
        this.postID = postID;
        this.solutionID = solutionID;
        this.solutionMediaID = solutionMediaID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + postID;
        result = prime * result + solutionID;
        result = prime * result + solutionMediaID;
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

        ESSMediaId other = (ESSMediaId) obj;
        return (postID == other.postID) && (solutionID == other.solutionID) && (solutionMediaID == other.solutionMediaID);
    }
}