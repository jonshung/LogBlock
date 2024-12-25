package com.logblock.backend.PostServiceTests;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Optional;
import java.util.Date;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.logblock.backend.DataSource.Model.Commenting;
import com.logblock.backend.DataSource.Model.CommentingId;
import com.logblock.backend.DataSource.Repository.CommentRepository;
import com.logblock.backend.PostService.CommentService;
import com.logblock.backend.PostService.PostService;
import com.logblock.backend.DataSource.DTO.PostingDTO;
import com.logblock.backend.DataSource.Model.Posting;

import jakarta.transaction.Transactional;

@SpringBootTest
@Transactional
@ActiveProfiles("test") // Use a test profile for unit tests
public class CommentServiceTest {

    @InjectMocks
    private CommentService commentService; // Service to be tested

    @Mock
    private CommentRepository commentRepository; // Mocked repository to simulate DB operations

    @Mock
    private PostService postService; // Mocked PostService to create posts

    private Commenting comment;
    private CommentingId commentId;
    private Posting createdPost;

    @BeforeEach
    public void setup() {
        // Initialize test data
        commentId = new CommentingId(1, 1); // postID = 1, commentID = 1
        comment = new Commenting();
        comment.setPostID(1);
        comment.setCommentID(1);
        comment.setCaption("This is a test comment.");
        comment.setCreationDate(new Date());

        // Create a post to associate comments with it
        PostingDTO postDTO = new PostingDTO(0, 1, "Test Post Caption", new Date(), new Date());
        when(postService.createPost(postDTO)).thenReturn(1); // Assuming the post ID is 1
        createdPost = new Posting();
        createdPost.setPostID(1);
        createdPost.setCaption("Test Post Caption");
        when(postService.getPost(1)).thenReturn(createdPost); // Simulating the retrieval of the post
    }

    // Test case for creating a comment
    @Test
    void testCreateComment() {
        when(commentRepository.addCommenting(1, comment)).thenReturn(1);

        int result = commentService.createComment(comment);

        assertEquals(1, result, "Comment creation should return the correct comment ID.");
        verify(commentRepository, times(1)).addCommenting(1, comment); // Ensure repository method was called
    }

    // Test case for updating an existing comment
    @Test
    void testUpdateComment() {
        Commenting updatedComment = new Commenting();
        updatedComment.setPostID(1);
        updatedComment.setCommentID(1);
        updatedComment.setCaption("Updated caption");
        updatedComment.setCreationDate(new Date());

        when(commentRepository.updateCommenting(commentId, updatedComment)).thenReturn(1);

        int result = commentService.updateComment(1, 1, updatedComment);

        assertEquals(1, result, "Updating comment should return the correct updated comment ID.");
        verify(commentRepository, times(1)).updateCommenting(commentId, updatedComment); // Ensure repository method was
                                                                                         // called
    }

    // Test case for retrieving a comment
    @Test
    void testGetComment() {
        when(commentRepository.findById(commentId)).thenReturn(Optional.of(comment));

        Optional<Commenting> result = commentService.getComment(1, 1);

        assertTrue(result.isPresent(), "Comment should be present when fetched by ID.");
        assertEquals(comment.getCommentCaption(), result.get().getCommentCaption(),
                "Fetched comment caption should match the original.");
    }

    // Test case for deleting a comment
    @Test
    void testDeleteComment() {
        when(commentRepository.removeCommenting(commentId)).thenReturn(1);

        int result = commentService.deleteComment(1, 1);

        assertEquals(1, result, "Deleting comment should return 1 for success.");
        verify(commentRepository, times(1)).removeCommenting(commentId); // Ensure repository method was called
    }

    // Test case for trying to update a non-existing comment
    @Test
    void testUpdateNonExistingComment() {
        Commenting updatedComment = new Commenting();
        updatedComment.setPostID(1);
        updatedComment.setCommentID(1);
        updatedComment.setCaption("Updated caption");
        updatedComment.setCreationDate(new Date());

        when(commentRepository.updateCommenting(commentId, updatedComment)).thenReturn(-1);

        int result = commentService.updateComment(1, 1, updatedComment);

        assertEquals(-1, result, "Attempting to update a non-existing comment should return -1.");
    }

    // Test case for trying to delete a non-existing comment
    @Test
    void testDeleteNonExistingComment() {
        when(commentRepository.removeCommenting(commentId)).thenReturn(0);

        int result = commentService.deleteComment(1, 1);

        assertEquals(0, result, "Attempting to delete a non-existing comment should return 0.");
    }
}
