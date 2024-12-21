package com.logblock.backend.DataSource.Model;

import java.io.Serializable;

public class ConnectionId implements Serializable {
    private static final long serialVersionUID = 1L;

    private final int connectorID;
    private final int connectedID;

    public ConnectionId() {
        this.connectorID = 0;
        this.connectedID = 0;
    }

    public ConnectionId(int connectorID, int connectedID) {
        this.connectorID = connectorID;
        this.connectedID = connectedID;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + connectorID;
        result = prime * result + connectedID;
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

        ConnectionId other = (ConnectionId) obj;
        return (connectorID == other.connectorID) && (connectedID == other.connectedID);
    }
}
