package com.logblock.backend.Controllers;

import com.logblock.backend.ProfileService.ConnectionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/connections")
public class ConnectionController {

    @Autowired
    private ConnectionService connectionService;

    /**
     * Create a new connection between two users.
     *
     * @param connectorID ID of the user initiating the connection
     * @param connectToID ID of the user to connect to
     * @return Response with the status of the connection creation
     */
    @PostMapping("/{connectorID}/{connectToID}")
    public ResponseEntity<?> createConnection(@PathVariable int connectorID, @PathVariable int connectToID) {
        int result = connectionService.createConnection(connectorID, connectToID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Delete a connection between two users.
     *
     * @param connectorID ID of the user initiating the removal
     * @param connectToID ID of the user to remove the connection with
     * @return Response with the status of the connection removal
     */
    @DeleteMapping("/{connectorID}/{connectToID}")
    public ResponseEntity<?> deleteConnection(@PathVariable int connectorID, @PathVariable int connectToID) {
        int result = connectionService.deleteConnection(connectorID, connectToID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
