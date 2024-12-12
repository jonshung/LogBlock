package com.logblock.backend.ProfileService;

import com.logblock.backend.DataSource.Model.Profile;
import com.logblock.backend.DataSource.Repository.ProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProfileService {

    @Autowired
    private ProfileRepository profileRepository;

    /**
     * Update user's biography.
     *
     * @param userID       ID of the user
     * @param newBiography The new biography
     * @return 1 if update is successful, otherwise 0
     */
    public int updateBiography(int userID, String newBiography) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setBiography(newBiography);
            profileRepository.save(profile);
            return 1; // Success
        }
        return 0; // Failure
    }

    /**
     * Update user's profile image.
     *
     * @param userID        ID of the user
     * @param newProfileImg URL or path of the new profile image
     * @return 1 if update is successful, otherwise 0
     */
    public int updateProfileImg(int userID, String newProfileImg) {
        Profile profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setProfileImg(newProfileImg);
            profileRepository.save(profile);
            return 1; // Success
        }
        return 0; // Failure
    }

    /**
     * Retrieve the user's profile information.
     *
     * @param userID ID of the user
     * @return Profile object if found, otherwise null
     */
    public Profile retrieveProfileInfo(int userID) {
        return profileRepository.findById(userID).orElse(null);
    }
}
