package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Integer> {

    /**
     * Retrieve all comments.
     *
     * @return List of all comments
     */
    @Query("SELECT c FROM Comment c")
    List<Comment> retrieveAllComments();

    /**
     * Retrieve a comment by postID and commentID.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment
     * @return Comment object if found, otherwise null
     */
    @Query("SELECT c FROM Comment c WHERE c.postID = :postID AND c.commentID = :commentID")
    Comment retrieveComment(int postID, int commentID);

    /**
     * Add a new comment to a post.
     *
     * @param postID     ID of the post
     * @param newComment The comment to be added
     * @return ID of the newly added comment
     */
    @Transactional
    default int addComment(int postID, Comment newComment) {
        newComment.setPostID(postID);
        Comment savedComment = save(newComment); // Use save() to persist the new comment
        return savedComment.getCommentID(); // Return the ID of the newly added comment
    }

    /**
     * Update an existing comment.
     *
     * @param postID         ID of the post
     * @param commentID      ID of the comment to update
     * @param commentContent Updated comment content
     * @return ID of the updated comment
     */
    @Transactional
    default int updateComment(int postID, int commentID, Comment commentContent) {
        Optional<Comment> existingCommentOpt = findById(commentID);
        if (existingCommentOpt.isPresent()) {
            Comment existingComment = existingCommentOpt.get();
            if (existingComment.getPostID() == postID) {
                existingComment.setCaption(commentContent.getCommentCaption());
                existingComment.setCreationDate(commentContent.getCommentCreationDate());
                save(existingComment); // Save the updated comment
                return existingComment.getCommentID();
            }
        }
        return -1; // Return -1 if comment does not exist or doesn't belong to the post
    }

    /**
     * Remove a comment by postID and commentID.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removeComment(int postID, int commentID) {
        Optional<Comment> commentOpt = findById(commentID);
        if (commentOpt.isPresent()) {
            Comment comment = commentOpt.get();
            if (comment.getPostID() == postID) {
                delete(comment); // Delete the comment from the database
                return 1;
            }
        }
        return 0; // Return 0 if comment does not exist or doesn't belong to the post
    }
}
