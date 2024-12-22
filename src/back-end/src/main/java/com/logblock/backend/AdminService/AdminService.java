package com.logblock.backend.AdminService;

import com.logblock.backend.DataSource.Model.Report;
import com.logblock.backend.DataSource.Repository.PostRepository;
import com.logblock.backend.DataSource.Repository.ReportRepository;
import com.logblock.backend.AuthenticationService.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AdminService {

    @Autowired
    private AccountService accountService;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ReportRepository reportRepository;

    /**
     * Retrieve all reports from the system.
     *
     * @return List of all reports
     */
    public List<Report> retrieveAllReports() {
        return reportRepository.retrieveAllReports(); // Retrieve all reports from the repository
    }

    /**
     * Remove a post from the system.
     *
     * @param postID ID of the post to remove
     * @return 1 if the post is removed successfully, otherwise 0
     */
    public int removePost(int postID) {
        return postRepository.removePost(postID); // Call PostRepository's remove method to delete the post
    }

    /**
     * Close a user account.
     *
     * @param userID ID of the user account to close
     * @return 1 if the account is closed successfully, otherwise 0
     */
    public int closeAccount(int userID) {
        return accountService.deleteAccount(userID); // Call AccountService's delete method to close the account
    }
}
