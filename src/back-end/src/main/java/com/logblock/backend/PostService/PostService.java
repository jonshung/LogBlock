package com.logblock.backend.PostService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Model.Report;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.DataSource.Repository.ReportRepository;

@Service
public class PostService {

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReportRepository reportRepository;

    /**
     * Update an existing post.
     *
     * @param postID   ID of the post to update
     * @param postInfo Updated post information
     * @return ID of the updated post
     */
    @Transactional
    public int updatePost(int postID, Posting postInfo) {
        return postRepository.updatePosting(postID, postInfo); // Call update method in repository
    }

    /**
     * Create a new post.
     *
     * @param postInfo The post information to create
     * @return ID of the created post
     */
    @Transactional
    public int createPost(Posting postInfo) {
        // The PostRepository's save method will handle both add and update
        postRepository.save(postInfo);
        return postInfo.getPostID(); // Return the generated ID of the created post
    }

    /**
     * Delete a post.
     *
     * @param postID ID of the post to delete
     * @return 1 if successful, otherwise 0
     */
    @Transactional
    public int deletePost(int postID) {
        // Use the repository's remove method
        return postRepository.removePost(postID); // Return 1 if successful, otherwise 0
    }

    /**
     * Retrieve post information.
     *
     * @param postID ID of the post
     * @return Post object if found, otherwise null
     */
    public Posting retrievePostInfo(int postID) {
        // Use the repository's findById method
        return postRepository.findById(postID).orElse(null); // Return null if not found
    }

    /**
     * Upvote a post.
     *
     * @param postID ID of the post to upvote
     * @param userID ID of the user upvoting the post
     * @return 1 if successful, otherwise 0
     */
    @Transactional
    public int upvotePost(int postID, int userID) {
        Posting post = postRepository.findById(postID).orElse(null);
        if (post != null) {
            throw new UnsupportedOperationException("Feature incomplete. Contact assistance.");
        }
        return 0; // Failure
    }

    /**
     * Report a post.
     *
     * @param postID ID of the post to report
     * @param userID ID of the user reporting the post
     * @return ID of the created report
     */
    @Transactional
    public int reportPost(int postID, int userID) {
        Posting post = postRepository.findById(postID).orElse(null);
        if (post != null) {
            Report report = new Report();
            report.setReportedPostID(postID);
            report.setReporterID(userID);
            report.setReportedDate(new java.util.Date());
            reportRepository.addReport(report); // Save the report in the repository
            return report.getReportID(); // Return the ID of the created report
        }
        return -1; // Failure if the post does not exist
    }
}
