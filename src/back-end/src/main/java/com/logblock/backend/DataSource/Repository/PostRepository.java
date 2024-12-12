package com.logblock.backend.DataSource.Repository;

import com.logblock.backend.DataSource.Model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
public interface PostRepository extends JpaRepository<Post, Integer> {

    /**
     * Retrieve posts from a list of user IDs.
     *
     * @param userIDs List of user IDs
     * @return List of posts from the given user IDs
     */
    List<Post> findPostsByUserIDs(List<Integer> userIDs);

    /**
     * Retrieve all posts.
     *
     * @return List of all posts
     */
    @Query("SELECT p FROM Post p")
    List<Post> retrieveAllPosts();

    /**
     * Retrieve a post by its ID.
     *
     * @param postID ID of the post
     * @return Post object if found, otherwise null
     */
    Optional<Post> retrievePost(int postID);

    /**
     * Custom method to add a new post (using JpaRepository save method).
     *
     * @param newPost Post object to be added
     * @return ID of the newly added post
     */
    default int addPost(Post newPost) {
        Post savedPost = save(newPost); // JpaRepository's save method will handle the persist.
        return savedPost.getPostID();
    }

    /**
     * Retrieve trending posts based on upvotes and views.
     *
     * @return List of trending posts ordered by upvotes and views
     */
    @Query("SELECT p FROM Post p ORDER BY p.upvoters.size DESC, p.views DESC")
    List<Post> findTrendingPosts();

    /**
     * Custom method to update an existing post (using JpaRepository save method).
     *
     * @param postID      ID of the post to update
     * @param postContent Updated post information
     * @return ID of the updated post
     */
    default int updatePost(int postID, Post postContent) {
        Optional<Post> existingPostOpt = retrievePost(postID);
        if (existingPostOpt.isPresent()) {
            Post existingPost = existingPostOpt.get();
            existingPost.setCaption(postContent.getCaption());
            existingPost.setMedia(postContent.getMedia());
            existingPost.setCreationDate(postContent.getCreationDate());
            existingPost.setLastModifiedDate(postContent.getLastModifiedDate());
            existingPost.setTags(postContent.getTags());
            existingPost.setUpvoters(postContent.getUpvoters());
            save(existingPost); // JpaRepository's save method will handle the update.
            return existingPost.getPostID();
        }
        return -1; // Return -1 if post does not exist
    }

    /**
     * Custom method to remove a post by its ID.
     *
     * @param postID ID of the post to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removePost(int postID) {
        Optional<Post> post = retrievePost(postID);
        if (post.isPresent()) {
            delete(post.get()); // JpaRepository's delete method will handle the removal.
            return 1;
        }
        return 0; // Return 0 if post does not exist
    }
}
