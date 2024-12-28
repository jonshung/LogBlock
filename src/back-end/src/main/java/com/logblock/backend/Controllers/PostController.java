package com.logblock.backend.Controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.logblock.backend.Controllers.FeedsController.FeedGenerationFilter;
import com.logblock.backend.DataSource.DTO.PostingDTO;
import com.logblock.backend.DataSource.DTO.PostingMediaDTO;
import com.logblock.backend.DataSource.DTO.ReportingDTO;
import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Model.PostingMedia;
import com.logblock.backend.PostService.PostService;

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
    public ResponseEntity<?> updatePost(@PathVariable int postID, @RequestBody PostingDTO postInfo) {
        int result = postService.updatePost(postID, PostingDTO.toPosting(postInfo));
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Create a new post.
     *
     * @param postInfo The post information to create
     * @return Response with the newly created post ID
     */
    @PostMapping( path = "/create/", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> createPost(@RequestBody PostingDTO postInfo) {
        int result = postService.createPost(postInfo);
        return result > 0 ? ResponseEntity.ok(result) : ResponseEntity.internalServerError().build();
    }

    /**
     * Upload media to a post.
     *
     * @param postInfo The post information to create
     * @return Response with the newly created post ID
     */
    @GetMapping( path = "/media/{postID}")
    public ResponseEntity<List<PostingMediaDTO>> getMedia(@PathVariable Integer postID) {
        List<PostingMedia> prep = postService.getPostingMedia(postID);
        List<PostingMediaDTO> res = new ArrayList<>();
        for(PostingMedia p : prep) {
            res.add(PostingMediaDTO.toDTO(p));
        }
        return new ResponseEntity<>(res, HttpStatus.OK);
    }

    /**
     * Upload media to a post.
     *
     * @param postInfo The post information to create
     * @return Response with the newly created post ID
     */
    @PostMapping( path = "/media/add", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Integer> addMedia(@RequestBody PostingMediaDTO postInfo) {
        int result = postService.addPostingMedia(postInfo);
        return result <= 0 ? ResponseEntity.badRequest().body(result) : ResponseEntity.ok(result);
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
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Retrieve post information.
     *
     * @param postID ID of the post to retrieve
     * @return Response with the post information
     */
    @GetMapping("/{postID}")
    public ResponseEntity<PostingDTO> retrievePostInfo(@PathVariable int postID) {
        Posting post = postService.getPost(postID);
        return post != null ? ResponseEntity.ok(PostingDTO.toDTO(post)) : ResponseEntity.noContent().build();
    }

    /**
     * Retrieve post information.
     *
     * @param userID ID of the post to retrieve
     * @return Response with the post information
     */
    @GetMapping("/by/{userID}")
    public ResponseEntity<?> retrievePostInfoByUser(@PathVariable int userID) {
        List<Posting> posts = postService.getPostByUser(userID);
        List<PostingDTO> result = new ArrayList<>();
        for(Posting p : posts) {
            result.add(PostingDTO.toDTO(p));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    /**
     * Retrieve post information.
     *
     * @param userID ID of the post to retrieve
     * @return Response with the post information
     */
    @PostMapping( path = "/by/{userID}/limit", headers = "Accept=application/json", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> retrievePostInfoByUserLimited(@PathVariable int userID, @RequestBody FeedGenerationFilter filter) {
        List<Integer> exclusion = new ArrayList<>();
        if(filter.getExcluding() != null) {
            exclusion = filter.getExcluding();
        }
        Integer limit = 5;
        if(filter.getLimit() != null) {
            limit = filter.getLimit();
        }
        List<Posting> posts = postService.getPostByUserLimited(userID, exclusion, limit);
        List<PostingDTO> result = new ArrayList<>();
        for(Posting p : posts) {
            result.add(PostingDTO.toDTO(p));
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
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
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Report a post.
     *
     * @param postID ID of the post to report
     * @param userID ID of the user reporting the post
     * @return Response with the status of the report
     */
    @PostMapping("/report/")
    public ResponseEntity<?> report(@RequestBody ReportingDTO dto) {
        int result = postService.reportPost(dto);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }
}
