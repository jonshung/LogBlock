package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.Connection;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface ConnectionRepository extends JpaRepository<Connection, Integer> {

    /**
     * Retrieve all connections.
     *
     * @return List of all connections
     */
    @Query("SELECT c FROM Connection c")
    List<Connection> retrieveAllConnections();

    /**
     * Retrieve a connection by its ID.
     *
     * @param connectionID ID of the connection
     * @return Connection object if found, otherwise null
     */
    @Override
    Optional<Connection> findById(Integer connectionID);

    /**
     * Add a new connection.
     *
     * @param newConnection The connection to be added
     * @return ID of the newly added connection
     */
    @Transactional
    default int addConnection(Connection newConnection) {
        Connection savedConnection = save(newConnection);
        return savedConnection.getConnectorID(); // Return the ID of the newly added connection
    }

    /**
     * Update an existing connection.
     *
     * @param connectionID      ID of the connection to update
     * @param connectionContent Updated connection content
     * @return ID of the updated connection
     */
    @Transactional
    default int updateConnection(int connectionID, Connection connectionContent) {
        Optional<Connection> existingConnectionOpt = findById(connectionID);
        if (existingConnectionOpt.isPresent()) {
            Connection existingConnection = existingConnectionOpt.get();
            existingConnection.setConnectedID(connectionContent.getConnectedID());
            existingConnection.setConnectedDate(connectionContent.getConnectedDate());
            save(existingConnection); // Save the updated connection
            return existingConnection.getConnectorID();
        }
        return -1; // Return -1 if connection does not exist
    }

    /**
     * Remove a connection by its ID.
     *
     * @param connectionID ID of the connection to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removeConnection(int connectionID) {
        Optional<Connection> connectionOpt = findById(connectionID);
        if (connectionOpt.isPresent()) {
            delete(connectionOpt.get()); // Delete the connection from the database
            return 1;
        }
        return 0; // Return 0 if connection does not exist
    }
}
