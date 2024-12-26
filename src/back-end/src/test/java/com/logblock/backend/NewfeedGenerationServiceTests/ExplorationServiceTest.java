package com.logblock.backend.NewfeedGenerationServiceTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.FeedsGenerationService.ExplorationFeedService;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import jakarta.transaction.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Date;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Sử dụng profile "test" cho unit tests
public class ExplorationServiceTest {

    @Mock
    private PostRepository postRepository; // Mock PostRepository

    @InjectMocks
    private ExplorationFeedService explorationFeedService; // Inject mock vào ExplorationFeedService

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this); // Khởi tạo các mock
    }

    @Test
    public void testGenerateTrendingFeedWithPosts() {
        // Arrange: Tạo mock dữ liệu bài đăng thịnh hành
        List<Posting> trendingPosts = new ArrayList<>();
        trendingPosts.add(new Posting(101, "Trending Post 1", new Date(), new Date()));
        trendingPosts.add(new Posting(102, "Trending Post 2", new Date(), new Date()));

        // Giả lập postRepository trả về danh sách trending posts.
        // Sử dụng findAllPostings() để giả lập trả về danh sách bài đăng thịnh hành.
        when(postRepository.findAllPostings()).thenReturn(trendingPosts);

        // Act: Gọi phương thức generate
        List<Posting> result = explorationFeedService.generate();

        // Assert: Kiểm tra kết quả
        assertEquals(2, result.size());
        assertEquals("Trending Post 1", result.get(0).getCaption());
        assertEquals("Trending Post 2", result.get(1).getCaption());

        verify(postRepository, times(1)).findAllPostings(); // Đảm bảo phương thức được gọi
    }

    @Test
    public void testGenerateTrendingFeedWithNoPosts() {
        // Arrange: Trường hợp không có bài đăng nào
        when(postRepository.findAllPostings()).thenReturn(new ArrayList<>()); // Mock trả về danh sách rỗng

        // Act: Gọi phương thức generate
        List<Posting> result = explorationFeedService.generate();

        // Assert: Kết quả phải rỗng
        assertEquals(0, result.size());
        verify(postRepository, times(1)).findAllPostings(); // Đảm bảo phương thức được gọi
    }

    @Test
    public void testGenerateTrendingFeedWithException() {
        // Arrange: Giả lập lỗi từ postRepository
        when(postRepository.findAllPostings()).thenThrow(new RuntimeException("Database error"));

        // Act & Assert: Gọi phương thức và kiểm tra exception
        RuntimeException exception = assertThrows(RuntimeException.class, () -> explorationFeedService.generate());
        assertEquals("Database error", exception.getMessage());

        verify(postRepository, times(1)).findAllPostings(); // Đảm bảo phương thức được gọi
    }
}
