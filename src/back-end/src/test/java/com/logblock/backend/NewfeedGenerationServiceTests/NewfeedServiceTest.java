package com.logblock.backend.NewfeedGenerationServiceTests;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.Model.Connection;
import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Repository.ConnectionRepository;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.FeedsGenerationService.NewFeedService;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Sử dụng profile "test" cho unit tests
public class NewfeedServiceTest {

    @Mock
    private PostRepository postRepository; // Mock PostRepository

    @Mock
    private ConnectionRepository connectionRepository; // Mock ConnectionRepository

    @InjectMocks
    private NewFeedService newFeedService; // Inject các mock vào NewFeedService

    private int userID;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Khởi tạo các mock
        userID = 1; // Đặt ID người dùng cho test
    }

    @Test
    public void testGenerateFeedWithValidConnections() {
        // Arrange: Tạo mock data cho user, connections, và posts
        List<Connection> connections = List.of(
                new Connection(userID, 2, new Date()),
                new Connection(userID, 3, new Date()));

        List<Posting> postsFromUser2 = List.of(new Posting(101, "Post by User 2", new Date(), new Date()));
        List<Posting> postsFromUser3 = List.of(new Posting(102, "Post by User 3", new Date(), new Date()));

        when(connectionRepository.findByConnectorID(userID)).thenReturn(connections);
        when(postRepository.findPostingsByUserID(2)).thenReturn(postsFromUser2);
        when(postRepository.findPostingsByUserID(3)).thenReturn(postsFromUser3);

        // Act: Gọi phương thức generate
        List<Posting> result = newFeedService.generate(userID, new ArrayList<>(), 10);

        // Assert: Kiểm tra kết quả
        assertEquals(2, result.size());
        assertEquals("Post by User 2", result.get(0).getCaption());
        assertEquals("Post by User 3", result.get(1).getCaption());

        verify(connectionRepository, times(1)).findByConnectorID(userID);
        verify(postRepository, times(1)).findPostingsByUserID(2);
        verify(postRepository, times(1)).findPostingsByUserID(3);
    }

    @Test
    public void testGenerateFeedWithNoConnections() {
        // Arrange: Không có kết nối
        when(connectionRepository.findByConnectorID(userID)).thenReturn(new ArrayList<>());

        // Act: Gọi phương thức generate
        List<Posting> result = newFeedService.generate(userID, new ArrayList<>(), 10);

        // Assert: Kết quả phải rỗng
        assertEquals(0, result.size());
        verify(connectionRepository, times(1)).findByConnectorID(userID);
        verifyNoInteractions(postRepository);
    }

    @Test
    public void testGenerateFeedWithEmptyPosts() {
        // Arrange: Có kết nối nhưng không có bài đăng
        List<Connection> connections = List.of(new Connection(userID, 2, new Date()));

        when(connectionRepository.findByConnectorID(userID)).thenReturn(connections);
        when(postRepository.findPostingsByUserID(2)).thenReturn(new ArrayList<>());

        // Act: Gọi phương thức generate
        List<Posting> result = newFeedService.generate(userID, new ArrayList<>(), 10);

        // Assert: Kết quả phải rỗng
        assertEquals(0, result.size());
        verify(connectionRepository, times(1)).findByConnectorID(userID);
        verify(postRepository, times(1)).findPostingsByUserID(2);
    }

    @Test
    public void testGenerateFeedWithNullConnections() {
        // Arrange: Giả lập trường hợp findByConnectorID trả về null
        when(connectionRepository.findByConnectorID(userID)).thenReturn(null);

        // Act: Gọi phương thức generate
        List<Posting> result = newFeedService.generate(userID, new ArrayList<>(), 10);

        // Assert: Kết quả phải rỗng
        assertNotNull(result, "The result should not be null even if connections are null");
        assertEquals(0, result.size(), "The result should be an empty list when connections are null");

        // Verify: Đảm bảo findByConnectorID được gọi đúng một lần
        verify(connectionRepository, times(1)).findByConnectorID(userID);

        // Verify: Không có tương tác với postRepository khi không có kết nối
        verifyNoInteractions(postRepository);
    }

    // @Test
    // public void testGenerateFeedWithException() {
    // // Arrange: Mô phỏng lỗi từ connectionRepository
    // when(connectionRepository.findByConnectorID(userID)).thenThrow(new
    // RuntimeException("Database error"));

    // // Act & Assert: Gọi phương thức và kiểm tra exception
    // RuntimeException exception = assertThrows(RuntimeException.class, () ->
    // newFeedService.generate(userID));
    // assertEquals("Database error", exception.getMessage());

    // verify(connectionRepository, times(1)).findByConnectorID(userID);
    // verifyNoInteractions(postRepository);
    // }
}
