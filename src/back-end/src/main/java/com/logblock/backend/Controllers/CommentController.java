package com.logblock.backend.Controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.CommentingDTO;
import com.logblock.backend.DataSource.Model.Commenting;
import com.logblock.backend.PostService.CommentService;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    /**
     * Update an existing comment.
     *
     * @param postID      ID of the post
     * @param commentID   ID of the comment
     * @param commentInfo Updated comment information
     * @return Response with the status of the update
     */
    @PutMapping("/{postID}/{commentID}")
    public ResponseEntity<?> updateComment(@PathVariable int postID, @PathVariable int commentID,
            @RequestBody Commenting commentInfo) {
        int result = commentService.updateComment(postID, commentID, commentInfo);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Create a new comment.
     *
     * @param commentInfo The comment to create
     * @return Response with the newly created comment ID
     */
    @PostMapping( path = "/add/", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createComment(@RequestBody CommentingDTO commentInfo) {
        int result = commentService.createComment(commentInfo);
        return ResponseEntity.ok(result);//
    }

    /**
     * Delete a comment.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment to delete
     * @return Response with the status of the deletion
     */
    @DeleteMapping("/del/{postID}/{commentID}")
    public ResponseEntity<?> deleteComment(@PathVariable int postID, @PathVariable int commentID) {
        int result = commentService.deleteComment(postID, commentID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Retrieve comment information.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment
     * @return Response with the comment information
     */
    @GetMapping("/{postID}/{commentID}")
    public ResponseEntity<?> retrieveCommentInfo(@PathVariable int postID, @PathVariable int commentID) {
        Optional<Commenting> comment = commentService.getComment(postID, commentID);
        return comment.isPresent() ? ResponseEntity.ok(CommentingDTO.toDTO(comment.get())) : ResponseEntity.noContent().build();
    }

    /**
     * Retrieve all comments information of a post.
     *
     * @param postID    ID of the post
     * @param commentID ID of the comment
     * @return Response with the comment information
     */
    @GetMapping("/{postID}")
    public ResponseEntity<?> retrieveAllCommentInfo(@PathVariable int postID) {
        List<Commenting> comments = commentService.getAllCommentsOfPost(postID);
        List<CommentingDTO> result = new ArrayList<>();
        for(Commenting c : comments) {
            result.add(CommentingDTO.toDTO(c));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }
}
