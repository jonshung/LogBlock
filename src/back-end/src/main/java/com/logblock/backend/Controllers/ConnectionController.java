package com.logblock.backend.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.ConnectionDTO;
import com.logblock.backend.DataSource.Model.Connection;
import com.logblock.backend.ProfileService.ConnectionService;


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
    public ResponseEntity<?> createConnection(@RequestBody ConnectionDTO dto) {
        int result = connectionService.createConnection(dto);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
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
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Get all connections initiated by user with id `userID`
     * 
     * @param userID
     * @return
     */
    @GetMapping("/from/{userID}")
    public ResponseEntity<?> getConnectionsFrom(@PathVariable int userID) {
        List<Connection> conns = connectionService.getAllFrom(userID);
        List<ConnectionDTO> result = new ArrayList<>();
        for(Connection c : conns) {
            result.add(ConnectionDTO.toDTO(c));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Get all connections to user with id `userID`
     * @param userID
     * @return
     */
    @GetMapping("/to/{userID}")
    public ResponseEntity<?> getConnectionsTo(@PathVariable int userID) {
        List<Connection> conns = connectionService.getAllTo(userID);
        List<ConnectionDTO> result = new ArrayList<>();
        for(Connection c : conns) {
            result.add(ConnectionDTO.toDTO(c));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
