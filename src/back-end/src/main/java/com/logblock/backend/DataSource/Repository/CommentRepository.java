package com.logblock.backend.DataSource.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logblock.backend.DataSource.Model.Commenting;
import com.logblock.backend.DataSource.Model.CommentingId;

import jakarta.transaction.Transactional;

@Repository
public interface CommentRepository extends JpaRepository<Commenting, CommentingId> {

    /**
     * Retrieve all comments.
     *
     * @return List of all comments
     */
    @Query("SELECT c FROM Commenting c")
    List<Commenting> findAllCommentings();

    /**
     * Add a new comment to a post.
     *
     * @return ID of the newly added comment
     */
    @Transactional
    default int addCommenting(Commenting c) {
        Commenting savedComment = save(c); // Use save() to persist the new comment
        return savedComment.getCommentID(); // Return the ID of the newly added comment
    }

    /**
     * Retrieve all comments.
     *
     * @return List of all comments
     */
    @Query("SELECT c FROM Commenting c WHERE c.postID = :postID")
    List<Commenting> findAllCommentingsOf(int postID);

    /**
     * Update an existing comment.
     *
     * @param postID         ID of the post
     * @param commentID      ID of the comment to update
     * @param commentContent Updated comment content
     * @return ID of the updated comment
     */
    @Transactional
    default int updateCommenting(CommentingId commentID, Commenting commentContent) {
        Optional<Commenting> existingCommentOpt = findById(commentID);
        if (existingCommentOpt.isPresent()) {
            Commenting existingComment = existingCommentOpt.get();
            existingComment.setCaption(commentContent.getCommentCaption());
            existingComment.setCreationDate(commentContent.getCommentCreationDate());
            save(existingComment); // Save the updated comment
            return existingComment.getCommentID();
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
    default int removeCommenting(CommentingId commentID) {
        Optional<Commenting> commentOpt = findById(commentID);
        if (commentOpt.isPresent()) {
            Commenting comment = commentOpt.get();
            delete(comment); // Delete the comment from the database
            return 1;
        }
        return 0; // Return 0 if comment does not exist or doesn't belong to the post
    }
}
