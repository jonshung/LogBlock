package com.logblock.backend.controllers;

import com.logblock.backend.PostService.PostService;
import com.logblock.backend.DataSource.Model.Post;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/posts")
public class PostController {

    @Autowired
    private PostService postService;

    /**
     * Update an existing post.
     *
     * @param postID   ID of the post to update
     * @param postInfo Updated post information
     * @return Response with the status of the operation
     */
    @PutMapping("/{postID}")
    public ResponseEntity<?> updatePost(@PathVariable int postID, @RequestBody Post postInfo) {
        int result = postService.updatePost(postID, postInfo);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Create a new post.
     *
     * @param postInfo The post information to create
     * @return Response with the newly created post ID
     */
    @PostMapping
    public ResponseEntity<?> createPost(@RequestBody Post postInfo) {
        int result = postService.createPost(postInfo);
        return ResponseEntity.ok(result);
    }

    /**
     * Delete a post.
     *
     * @param postID ID of the post to delete
     * @return Response with the status of the operation
     */
    @DeleteMapping("/{postID}")
    public ResponseEntity<?> deletePost(@PathVariable int postID) {
        int result = postService.deletePost(postID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Retrieve post information.
     *
     * @param postID ID of the post to retrieve
     * @return Response with the post information
     */
    @GetMapping("/{postID}")
    public ResponseEntity<Post> retrievePostInfo(@PathVariable int postID) {
        Post post = postService.retrievePostInfo(postID);
        return post != null ? ResponseEntity.ok(post) : ResponseEntity.notFound().build();
    }

    /**
     * Upvote a post.
     *
     * @param postID ID of the post to upvote
     * @param userID ID of the user upvoting the post
     * @return Response with the status of the upvote
     */
    @PostMapping("/{postID}/upvote/{userID}")
    public ResponseEntity<?> upvotePost(@PathVariable int postID, @PathVariable int userID) {
        int result = postService.upvotePost(postID, userID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Report a post.
     *
     * @param postID ID of the post to report
     * @param userID ID of the user reporting the post
     * @return Response with the status of the report
     */
    @PostMapping("/{postID}/report/{userID}")
    public ResponseEntity<?> reportPost(@PathVariable int postID, @PathVariable int userID) {
        int result = postService.reportPost(postID, userID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
