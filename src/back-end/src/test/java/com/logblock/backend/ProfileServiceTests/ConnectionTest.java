package com.logblock.backend.ProfileServiceTests;

import java.util.List;
import java.util.Date;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.DTO.ConnectionDTO;
import com.logblock.backend.DataSource.Repository.ConnectionRepository;
import com.logblock.backend.ProfileService.ConnectionService;
import com.logblock.backend.DataSource.Model.Connection;

import jakarta.transaction.Transactional;

/**
 * Template for service and data access testing for LogBlock.
 */
@SpringBootTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@Transactional
@ActiveProfiles("test")
public class ConnectionTest {

    // Start from here.
    @Autowired
    private ConnectionService connectionService;

    @Autowired
    private ConnectionRepository connectionRepository;
    private int connectorID;
    private int connectedID;

    @BeforeEach
    public void setUp() {
        // Giả sử connectorID và connectedID được tạo từ một quá trình trước đó
        connectorID = 1; // ID của người kết nối
        connectedID = 2; // ID của người được kết nối
    }

    // Test tạo kết nối
    @Test
    void testCreateConnection() {
        // Tạo đối tượng ConnectionDTO với thông tin hợp lệ
        ConnectionDTO dto = new ConnectionDTO(connectorID, connectedID, new Date());

        // Tạo kết nối
        int result = connectionService.createConnection(dto);

        // Kiểm tra kết quả trả về (1 tức là kết nối thành công)
        assertEquals(1, result, "Kết nối không thành công!");

        // Kiểm tra kết nối đã được lưu vào cơ sở dữ liệu
        List<Connection> connections = connectionRepository.findAllConnections();
        assertFalse(connections.isEmpty(), "Cơ sở dữ liệu không có kết nối!");

        // Kiểm tra kết nối có tồn tại trong cơ sở dữ liệu không
        Connection createdConnection = connections.stream()
                .filter(c -> c.getConnectorID() == connectorID && c.getConnectedID() == connectedID)
                .findFirst()
                .orElse(null);

        assertNotNull(createdConnection, "Kết nối không được tìm thấy trong cơ sở dữ liệu!");
        assertEquals(connectorID, createdConnection.getConnectorID(), "ID người kết nối không đúng!");
        assertEquals(connectedID, createdConnection.getConnectedID(), "ID người được kết nối không đúng!");
    }

    // Test xóa kết nối (điều này giả sử là phương thức deleteConnection đã được
    // hoàn thành)
    @Test
    void testDeleteConnection() {
        // Tạo kết nối trước
        ConnectionDTO dto = new ConnectionDTO(connectorID, connectedID, new Date());
        connectionService.createConnection(dto);

        // Lấy danh sách kết nối trước khi xóa
        List<Connection> connectionsBeforeDelete = connectionRepository.findAllConnections();
        assertFalse(connectionsBeforeDelete.isEmpty(), "Trước khi xóa, cơ sở dữ liệu không có kết nối!");

        // Giả sử deleteConnection đã được hoàn thành, ta xóa kết nối
        int deleteResult = connectionService.deleteConnection(connectorID, connectedID);

        // Kiểm tra kết quả trả về (1 tức là xóa thành công)
        assertEquals(1, deleteResult, "Xóa kết nối không thành công!");

        // Kiểm tra kết nối đã bị xóa
        List<Connection> connectionsAfterDelete = connectionRepository.findAllConnections();
        assertTrue(connectionsAfterDelete.isEmpty(), "Cơ sở dữ liệu vẫn còn kết nối sau khi xóa!");
    }
}
