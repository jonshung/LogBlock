package com.logblock.backend.PostService;

import com.logblock.backend.DataSource.Model.Comment;
import com.logblock.backend.DataSource.Repository.CommentRepository;
import com.logblock.backend.DataSource.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Update an existing comment.
     *
     * @param postID      ID of the post to update
     * @param commentID   ID of the comment to update
     * @param commentInfo Updated comment information
     * @return ID of the updated comment
     */
    public int updateComment(int postID, int commentID, Comment commentInfo) {
        return commentRepository.updateComment(postID, commentID, commentInfo);
    }

    /**
     * Create a new comment.
     *
     * @param commentInfo The comment information to create
     * @return ID of the created comment
     */
    public int createComment(Comment commentInfo) {
        // Assuming the postID is part of the commentInfo, otherwise, you would need to
        // pass postID separately
        return commentRepository.addComment(commentInfo.getPostID(), commentInfo);
    }

    /**
     * Delete a comment.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment to delete
     * @return 1 if successful, otherwise 0
     */
    public int deleteComment(int postID, int commentID) {
        return commentRepository.removeComment(postID, commentID);
    }

    /**
     * Retrieve comment information.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment
     * @return Comment object if found, otherwise null
     */
    public Comment retrievePostInfo(int postID, int commentID) {
        return commentRepository.retrieveComment(postID, commentID);
    }
}
