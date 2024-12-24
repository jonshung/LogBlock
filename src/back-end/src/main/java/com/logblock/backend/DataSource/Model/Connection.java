package com.logblock.backend.DataSource.Model;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;

@Entity
@Table(name = "Connection")
@IdClass(ConnectionId.class)
public class Connection {

    @Id
    @Column(name = "connector")
    private int connectorID;

    @Id
    @Column(name = "connectedto")
    private int connectedID;
    @Column(name = "connectiondate")
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
    public void setConnectedDate(Date newConnectedDate) {
        this.connectionDate = newConnectedDate;
    }

    public void setConnectedID(int newConnectedID) {
        this.connectedID = newConnectedID;
    }

    public void setConnectorID(int newConnectorID) {
        this.connectorID = newConnectorID;
    }
}
