package com.logblock.backend.ProfileService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.DTO.ConnectionDTO;
import com.logblock.backend.DataSource.Model.Connection;
import com.logblock.backend.DataSource.Model.ConnectionId;
import com.logblock.backend.DataSource.Repository.ConnectionRepository;

@Service
public class ConnectionService {

    @Autowired
    private ConnectionRepository connectionRepository;

    /**
     * Create a connection between two users.
     *
     * @param connectorID ID of the user initiating the connection
     * @param connectToID ID of the user to connect with
     * @return 1 if connection is created successfully, otherwise 0
     */
    public int createConnection(ConnectionDTO dto) {
        Connection connection = ConnectionDTO.toConnection(dto);
        connection.setConnectedDate(new Date());
        connectionRepository.addConnection(connection);
        return 1; // Success
    }

    /**
     * Delete a connection between two users.
     *
     * @param connectorID ID of the user initiating the removal
     * @param connectToID ID of the user to remove the connection with
     * @return 1 if connection is deleted successfully, otherwise 0
     */
    public int deleteConnection(int connectorID, int connectToID) {
        return connectionRepository.removeConnection(new ConnectionId(connectorID, connectToID));
    }

    /**
     * Get all connections from a user
     * 
     * @param connectorID
     * @return
     */
    public List<Connection> getAllFrom(int connectorID) {
        return connectionRepository.findByConnectorID(connectorID);
    }

    /**
     * Get all connections to a user
     * 
     * @param connectorID
     * @return
     */
    public List<Connection> getAllTo(int connectedToID) {
        return connectionRepository.findByConnectedID(connectedToID);
    }
}
