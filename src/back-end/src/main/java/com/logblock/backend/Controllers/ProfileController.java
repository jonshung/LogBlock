package com.logblock.backend.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.logblock.backend.DataSource.DTO.ProfileDTO;
import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.ProfileService.ProfileService;


@RestController
@RequestMapping("/profiles")
public class ProfileController {

    @Autowired
    private ProfileService profileService;

    /**
     * Update the biography of a user's profile.
     *
     * @param userID       ID of the user
     * @param newBiography New biography text
     * @return Response with the status of the operation
     */
    @PutMapping("/{userID}/biography")
    public ResponseEntity<?> updateBiography(@PathVariable int userID, @RequestBody String newBiography) {
        int result = profileService.updateBiography(userID, newBiography);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Update the profile image of a user's profile.
     *
     * @param userID        ID of the user
     * @param newProfileImg New profile image URL
     * @return Response with the status of the operation
     */
    @PutMapping("/{userID}/profile-image")
    public ResponseEntity<?> updateProfileImg(@PathVariable int userID, @RequestBody String newProfileImg) {
        int result = profileService.updateProfileImg(userID, newProfileImg);
        return result > 0 ? ResponseEntity.ok().build() : ResponseEntity.internalServerError().build();
    }

    /**
     * Retrieve the profile information of a user.
     *
     * @param userID ID of the user
     * @return Response with the profile information
     */
    @GetMapping("/{userID}")
    public ResponseEntity<ProfileDTO> retrieveProfileInfo(@PathVariable int userID) {
        Profile profile = profileService.getProfileByID(userID);
        return profile != null ? ResponseEntity.ok(ProfileDTO.toDTO(profile)) : ResponseEntity.noContent().build();
    }

    /**
     * Retrieve the profile information of a user.
     *
     * @param userID ID of the user
     * @return Response with the profile information
     */
    @GetMapping("/email/{userEmail}")
    public ResponseEntity<ProfileDTO> retrieveProfileInfo(@PathVariable String userEmail) {
        Profile profile = profileService.getProfileByEmail(userEmail);
        return profile != null ? ResponseEntity.ok(ProfileDTO.toDTO(profile)) : ResponseEntity.noContent().build();
    }

    @GetMapping("/")
    public ResponseEntity<Integer> getMe() {
        Profile u = profileService.getMe();
        if(u == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(u.getUserID());
    }
}
