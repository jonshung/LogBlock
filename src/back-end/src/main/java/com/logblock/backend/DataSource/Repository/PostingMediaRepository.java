package com.logblock.backend.DataSource.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.logblock.backend.DataSource.Model.PostingMedia;
import com.logblock.backend.DataSource.Model.PostingMediaId;

import jakarta.transaction.Transactional;

@Repository
public interface PostingMediaRepository extends JpaRepository<PostingMedia, PostingMediaId> {

    /**
     * Retrieve posts from a list of user IDs.
     *
     * @param userID User ID
     * @return List of posts media from the given user IDs
     */
    @Query("SELECT p FROM PostingMedia p WHERE p.postID = :postID")
    List<PostingMedia> findAllMediaOf(int postID);

    /**
     * Custom method to add a new post media (using JpaRepository save method).
     *
     * @param newPostMedia Post object to be added
     * @return ID of the newly added post
     */
    default int addPostingMedia(PostingMedia newPostMedia) {
        PostingMedia savedPostMedia = save(newPostMedia); // JpaRepository's save method will handle the persist.
        return savedPostMedia.getPostID();
    }

    /**
     * Custom method to remove a post by its ID.
     *
     * @param id ID of the post media to remove
     * @return 1 if the operation is successful, otherwise 0
     */
    @Transactional
    default int removePostingMedia(PostingMediaId id) {
        Optional<PostingMedia> postMedia = findById(id);
        if (postMedia.isPresent()) {
            delete(postMedia.get()); // JpaRepository's delete method will handle the removal.
            return 1;
        }
        return 0; // Return 0 if post does not exist
    }
}
