package com.logblock.backend.DataSource.Model;

import jakarta.persistence.*;
import java.util.Date;

@Entity
public class Connection {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int connectorID;

    private int connectedID;
    private Date connectionDate;

    // Getters
    public int getConnectorID() {
        return connectorID;
    }

    public int getConnectedID() {
        return connectedID;
    }

    public Date getConnectedDate() {
        return connectionDate;
    }

    // Setters
    public void setConnectorID(int newConnectorID) {
        this.connectorID = newConnectorID;
    }

    public void setConnectedID(int newConnectedID) {
        this.connectedID = newConnectedID;
    }

    public void setConnectedDate(Date newConnectedDate) {
        this.connectionDate = newConnectedDate;
    }
}
