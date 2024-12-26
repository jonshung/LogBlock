package com.logblock.backend.PostService;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.logblock.backend.DataSource.DTO.PostingDTO;
import com.logblock.backend.DataSource.DTO.ReportingDTO;
import com.logblock.backend.DataSource.Model.Posting;
import com.logblock.backend.DataSource.Model.Reporting;
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
    public int createPost(PostingDTO dto) {
        // The PostRepository's save method will handle both add and update
        Posting p = PostingDTO.toPosting(dto);
        postRepository.addPosting(p);
        return p.getPostID(); // Return the generated ID of the created post
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
    public Posting getPost(int postID) {
        // Use the repository's findById method
        return postRepository.findById(postID).orElse(null); // Return null if not found
    }

    /**
     * Retrieve posts information by User `userID`.
     *
     * @param userID ID of the post
     * @return Posting list.
     */
    public List<Posting> getPostByUser(int userID) {
        // Use the repository's findById method
        return postRepository.findPostingsByUserID(userID);
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
    public int reportPost(ReportingDTO dto) {
        Posting post = postRepository.findById(dto.reportedPostID).orElse(null);
        if (post != null) {
            Reporting report = ReportingDTO.toReporting(dto);
            report.setReportedDate(new Date());
            reportRepository.addReport(report); // Save the report in the repository
            return report.getReportID(); // Return the ID of the created report
        }
        return -1; // Failure if the post does not exist
    }
}
