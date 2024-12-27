package com.logblock.backend.NewfeedGenerationServiceTests;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.FeedsGenerationService.ExplorationFeedService;

import jakarta.transaction.Transactional;

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

        // Giả lập việc findTrendingPosts trả về danh sách các bài đăng thịnh hành
        when(postRepository.findTrendingPosts(5)).thenReturn(trendingPosts);

        // Act: Gọi phương thức generate
        List<Posting> result = explorationFeedService.generate(new ArrayList<>(), 5);

        // Assert: Kiểm tra kết quả
        assertEquals(2, result.size(), "The result should contain 2 trending posts");
        assertEquals("Trending Post 1", result.get(0).getCaption(), "The first post caption should match");
        assertEquals("Trending Post 2", result.get(1).getCaption(), "The second post caption should match");

        // Verify: Đảm bảo phương thức findTrendingPosts() được gọi đúng một lần
        verify(postRepository, times(1)).findTrendingPosts(5);
    }

    @Test
    public void testGenerateTrendingFeedWithNoPosts() {
        // Arrange: Giả lập (mock) rằng không có bài đăng nào được tìm thấy
        when(postRepository.findTrendingPosts(5)).thenReturn(Collections.emptyList());

        // Act: Gọi phương thức generate
        List<Posting> result = explorationFeedService.generate(new ArrayList<>(), 5);

        // Assert: Đảm bảo kết quả trả về là rỗng
        assertTrue(result.isEmpty(), "The result should be an empty list");

        // Verify: Đảm bảo phương thức findTrendingPosts() được gọi đúng một lần
        verify(postRepository, times(1)).findTrendingPosts(5);
    }

    // @Test
    // public void testGenerateTrendingFeedWithException() {
    // // Arrange: Giả lập lỗi từ postRepository
    // when(postRepository.findAllPostings()).thenThrow(new
    // RuntimeException("Database error"));

    // // Act & Assert: Gọi phương thức và kiểm tra exception
    // RuntimeException exception = assertThrows(RuntimeException.class, () ->
    // explorationFeedService.generate());
    // assertEquals("Database error", exception.getMessage());

    // verify(postRepository, times(1)).findAllPostings(); // Đảm bảo phương thức
    // được gọi
    // }
}
