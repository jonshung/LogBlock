package com.logblock.backend.Controllers;

import com.logblock.backend.AdminService.AdminService;
import com.logblock.backend.DataSource.Model.Report;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    /**
     * Retrieve all reports in the system.
     *
     * @return Response with the list of reports
     */
    @GetMapping("/reports")
    public ResponseEntity<List<Report>> retrieveAllReports() {
        List<Report> reports = adminService.retrieveAllReports();
        return ResponseEntity.ok(reports);
    }

    /**
     * Remove a post by its ID.
     *
     * @param postID ID of the post to remove
     * @return Response with the status of the removal
     */
    @DeleteMapping("/posts/{postID}")
    public ResponseEntity<?> removePost(@PathVariable int postID) {
        int result = adminService.removePost(postID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }

    /**
     * Close a user account.
     *
     * @param userID ID of the user to close the account
     * @return Response with the status of the account closure
     */
    @DeleteMapping("/users/{userID}")
    public ResponseEntity<?> closeAccount(@PathVariable int userID) {
        int result = adminService.closeAccount(userID);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.notFound().build();
    }
}
