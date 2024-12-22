package com.logblock.backend.ProfileService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.User;
import com.logblock.backend.DataSource.Repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository profileRepository;

    /**
     * Update user's biography.
     *
     * @param userID       ID of the user
     * @param newBiography The new biography
     * @return 1 if update is successful, otherwise 0
     */
    public int updateBiography(int userID, String newBiography) {
        User profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setBioDesc(newBiography);
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
        User profile = profileRepository.findById(userID).orElse(null);
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
    public User retrieveProfileInfo(int userID) {
        return profileRepository.findById(userID).orElse(null);
    }
}
