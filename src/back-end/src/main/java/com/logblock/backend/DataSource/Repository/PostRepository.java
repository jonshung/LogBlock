package com.logblock.backend.DataSource.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logblock.backend.DataSource.Model.Posting;

import jakarta.transaction.Transactional;

@Repository
public interface PostRepository extends JpaRepository<Posting, Integer> {

    /**
     * Retrieve posts from a list of user IDs.
     *
     * @param userID User ID
     * @return List of posts from the given user IDs
     */
    @Query("SELECT p FROM Posting p WHERE p.originalAuthor = :userID")
    List<Posting> findPostingsByUserID(int userID);

    /**
     * Retrieve all posts.
     *
     * @return List of all posts
     */
    @Query("SELECT p FROM Posting p")
    List<Posting> findAllPostings();

    /**
     * Custom method to add a new post (using JpaRepository save method).
     *
     * @param newPost Post object to be added
     * @return ID of the newly added post
     */
    default int addPosting(Posting newPost) {
        Posting savedPost = save(newPost); // JpaRepository's save method will handle the persist.
        return savedPost.getPostID();
    }

    /**
     * Custom method to update an existing post (using JpaRepository save method).
     *
     * @param postID      ID of the post to update
     * @param postContent Updated post information
     * @return ID of the updated post
     */
    default int updatePosting(int postID, Posting postContent) {
        Optional<Posting> existingPostOpt = findById(postID);
        if (existingPostOpt.isPresent()) {
            Posting existingPost = existingPostOpt.get();
            existingPost.setCaption(postContent.getCaption());
            existingPost.setCreationDate(postContent.getCreationDate());
            existingPost.setLastModifiedDate(postContent.getLastModifiedDate());
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
        Optional<Posting> post = findById(postID);
        if (post.isPresent()) {
            delete(post.get()); // JpaRepository's delete method will handle the removal.
            return 1;
        }
        return 0; // Return 0 if post does not exist
    }
}
