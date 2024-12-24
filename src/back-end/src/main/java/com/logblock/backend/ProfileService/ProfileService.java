package com.logblock.backend.ProfileService;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logblock.backend.DataSource.Model.User;
import com.logblock.backend.DataSource.Repository.UserRepository;

@Service
public class ProfileService {

    @Autowired
    private UserRepository profileRepository;

    /**
     * Update user's display name.
     * 
     * @param userID
     * @param newDisplayName
     * @return
     */
    public int updateDisplayName(int userID, String newDisplayName) {
        User profile = profileRepository.findById(userID).orElse(null);
        if(profile == null) {
            return 0;
        }
        profile.setDisplayName(newDisplayName);
        profileRepository.save(profile);
        return 0;
    }
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
     * Update user's privilege level.
     * 
     * @param userID
     * @param newPrivilegeLevel
     * @return
     */
    public int updatePrivilegeLevel(int userID, int newPrivilegeLevel) {
        User profile = profileRepository.findById(userID).orElse(null);
        if (profile != null) {
            profile.setPrivLevel(newPrivilegeLevel);
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
    public User getProfileByID(int userID) {
        return profileRepository.findById(userID).orElse(null);
    }

    /**
     * Retrieve the user's profile information by email.
     * 
     * @param email
     * @return
     */
    public User getProfileByEmail(String email) {
        List<User> results = profileRepository.findUserByUserEmail(email);
        if(results.isEmpty()) return null;
        return results.get(0);
    }
}
