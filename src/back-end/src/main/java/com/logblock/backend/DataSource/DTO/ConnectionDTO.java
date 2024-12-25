package com.logblock.backend.DataSource.DTO;

import java.util.Date;

import com.logblock.backend.DataSource.Model.Connection;

public class ConnectionDTO {
    
    public int connectorID;
    public int connectedID;
    public Date connectionDate;

    public ConnectionDTO(int connectorID, int connectedID, Date connectionDate) {
        this.connectorID = connectorID;
        this.connectedID = connectedID;
        this.connectionDate = connectionDate;
    }

    public static Connection toConnection(ConnectionDTO dto) {
        return new Connection(dto.connectorID, dto.connectedID, dto.connectionDate);
    }

    public static ConnectionDTO toDTO(Connection c) {
        return new ConnectionDTO(c.getConnectorID(), c.getConnectedID(), c.getConnectedDate());
    }
}
