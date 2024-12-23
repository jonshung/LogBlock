package com.logblock.backend.PostService;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.Commenting;
import com.logblock.backend.DataSource.Model.CommentingId;
import com.logblock.backend.DataSource.Repository.CommentRepository;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    /**
     * Get a comment, returning an Optional that present if the entry is found.
     * 
     * @param postID
     * @param commentID
     * @return
     */
    public Optional<Commenting> getComment(int postID, int commentID) {
        return commentRepository.findById(new CommentingId(postID, commentID));
    }
    /**
     * Update an existing comment.
     *
     * @param postID      ID of the post to update
     * @param commentID   ID of the comment to update
     * @param commentInfo Updated comment information
     * @return ID of the updated comment
     */
    public int updateComment(int postID, int commentID, Commenting commentInfo) {
        return commentRepository.updateCommenting(new CommentingId(postID, commentID), commentInfo);
    }

    /**
     * Create a new comment.
     *
     * @param commentInfo The comment information to create
     * @return ID of the created comment
     */
    public int createComment(Commenting commentInfo) {
        // Assuming the postID is part of the commentInfo, otherwise, you would need to
        // pass postID separately
        return commentRepository.addCommenting(commentInfo.getPostID(), commentInfo);
    }

    /**
     * Delete a comment.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment to delete
     * @return 1 if successful, otherwise 0
     */
    public int deleteComment(int postID, int commentID) {
        return commentRepository.removeCommenting(new CommentingId(postID, commentID));
    }
}
