package com.logblock.backend.Controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.AdminService.AdminService;
import com.logblock.backend.DataSource.DTO.ReportingDTO;
import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Model.Reporting;
import com.logblock.backend.ProfileService.ProfileService;

@RestController
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private AdminService adminService;

    @Autowired
    private ProfileService profileService;
    /**
     * Retrieve all reports in the system.
     *
     * @return Response with the list of reports
     */
    @GetMapping("/reports")
    public ResponseEntity<List<ReportingDTO>> retrieveAllReports() {
        List<Reporting> reports = adminService.retrieveAllReports();
        List<ReportingDTO> ret = new ArrayList<>();
        for(Reporting r : reports) {
            ret.add(ReportingDTO.toDTO(r));
        }
        return ResponseEntity.ok(ret);
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

    /**
     * Check for administrative privilege
     *
     * @param userID ID of the user to close the account
     * @return Response with the status of the administration
     */
    @GetMapping("/")
    public ResponseEntity<?> checkAdmin() {
        Profile u = profileService.getMe();
        if(u == null) {
            return new ResponseEntity<>(HttpStatus.FORBIDDEN);
        }
        return ResponseEntity.ok().build();
    }
}
