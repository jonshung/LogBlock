package com.logblock.backend.ProfileService;

import com.logblock.backend.DataSource.Repository.ConnectionRepository;
import com.logblock.backend.DataSource.Model.Connection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
    public int createConnection(int connectorID, int connectToID) {
        Connection connection = new Connection();
        connection.setConnectorID(connectorID);
        connection.setConnectedID(connectToID);
        connection.setConnectedDate(new java.util.Date());
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
        return deleteConnection(connectorID, connectToID);
    }
}
